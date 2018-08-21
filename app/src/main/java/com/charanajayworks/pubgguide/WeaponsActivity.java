package com.charanajayworks.pubgguide;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.charanajayworks.pubgguide.cards.SliderAdapter;
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;

import java.util.ArrayList;


public class WeaponsActivity extends AppCompatActivity {

    ArrayList<AssaultsAdapter> assaultsList;

    private final String[] imageUrls = {"https://i.imgur.com/0k7ZFWr.png", "https://i.imgur.com/zgfiWXw.png", "https://i.imgur.com/bShCyeV.png", "https://i.imgur.com/NJEJUeJ.png", "https://i.imgur.com/ZKo0HYi.png"};

    private final SliderAdapter sliderAdapter = new SliderAdapter(imageUrls, imageUrls.length, new OnCardClickListener());

    private CardSliderLayoutManager layoutManger;
    private RecyclerView recyclerView;
    private TextSwitcher ammoSwitcher;

    private TextView weapon1TextView;
    private TextView weapon2TextView;
    private int weaponOffset1;
    private int weaponOffset2;
    private long weaponAnimDuration;
    private int currentPosition;
    private String weaponCategory;

    private TextSwitcher descriptionSwitcher;
    private TextSwitcher magazineSizeSwitcher;
    private TextSwitcher timeBnShotsSwitcher;
    private TextSwitcher firingModesSwitcher;
    private TextSwitcher fullReloadSwitcher;
    private TextSwitcher tacticalReloadSwitcher;
    private TextSwitcher reloadMethodSwitcer;
    private TextSwitcher pickupSwitcher;
    private TextSwitcher readySwitcher;

    private ProgressBar hitProgress;
    private ProgressBar bulletProgress;
    private ProgressBar impactProgress;
    private ProgressBar rangeProgress;

    private LinearLayout airDropLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.weapon_activity_main);

        weaponCategory = getIntent().getExtras().getString("weaponType");

        assaultsList = new ArrayList<AssaultsAdapter>();

        assaultsList.add(new AssaultsAdapter("AKM", "https://i.imgur.com/0k7ZFWr.png", "For comrades-in-arms.", "30", "0.100s", "SINGLE, AUTO", "MAGAZINE", "2.900s", "2.250s", "0.15 s", "0.4 s", "7.62mm", "no", 715, 10000, 380, 49));
        assaultsList.add(new AssaultsAdapter("AUG", "https://i.imgur.com/zgfiWXw.png", "Modern bullpup assault rifle.", "30", "0.086s", "SINGLE, AUTO", "MAGAZINE", "3.100s", "3.000s", "0.25s", "-", "5.56mm", "yes", 940, 9000, 350, 43));
        assaultsList.add(new AssaultsAdapter("GROZA", "https://i.imgur.com/bShCyeV.png", "A selective fire Russian bullpup assault rifle chambered for a 7.62mm round.", "30", "0.080s", "SINGLE, AUTO", "MAGAZINE", "3.000s", "2.250s", "0.15 s", "0.4 s", "7.62mm", "yes", 715, 10000, 380, 49));
        assaultsList.add(new AssaultsAdapter("M16A4", "https://i.imgur.com/NJEJUeJ.png", "Burstfire assault rifle.", "30", "0.075s", "SINGLE, BURST", "MAGAZINE", "2.200s", "1.900s", "0.15 s", "0.5 s", "5.56mm", "no", 900, 8000, 360, 43));
        assaultsList.add(new AssaultsAdapter("Vertical Foregrip (AR, SMG, DMR)", "https://i.imgur.com/ZKo0HYi.png", "German do things properly.", "30", "0.086s", "SINGLE, AUTO", "MAGAZINE", "2.100s", "1.900s", "0.15 s", "0.5 s", "5.56mm", "no", 880, 3500, 400, 43));

        initRecyclerView();
        initWeaponTitle();
        initSwitchers();
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(sliderAdapter);
        recyclerView.setHasFixedSize(true);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    onActiveCardChange();
                }
            }
        });

        layoutManger = (CardSliderLayoutManager) recyclerView.getLayoutManager();


        new CardSnapHelper().attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void initSwitchers() {
        AssaultsAdapter currentAssault = assaultsList.get(0);

        ammoSwitcher = findViewById(R.id.ts_ammo);
        ammoSwitcher.setFactory(new TextViewFactory(R.style.TemperatureTextView, true));
        ammoSwitcher.setCurrentText(currentAssault.getAmmo());

        descriptionSwitcher = findViewById(R.id.weapon_description);
        descriptionSwitcher.setFactory(new TextViewFactory(R.style.WeaponDescriptionTextView, true));
        descriptionSwitcher.setInAnimation(this, R.anim.fade_in);
        descriptionSwitcher.setOutAnimation(this, R.anim.fade_out);
        descriptionSwitcher.setCurrentText(currentAssault.getWeaponDesc());

        hideOrShowAirDrop(currentAssault.getAirDrop());

        magazineSizeSwitcher = findViewById(R.id.ammo_mag_value);
        magazineSizeSwitcher.setFactory(new TextViewFactory(R.style.MagazineSizeSwitcher, false));
        magazineSizeSwitcher.setCurrentText(currentAssault.getMagazineSize());

        timeBnShotsSwitcher = findViewById(R.id.timebtnshots_value);
        timeBnShotsSwitcher.setFactory(new TextViewFactory(R.style.TimeBnSwitcher, false));
        timeBnShotsSwitcher.setCurrentText(currentAssault.getTimeBnShots());

        firingModesSwitcher = findViewById(R.id.firing_modes_value);
        firingModesSwitcher.setFactory(new TextViewFactory(R.style.FiringModeSwitcher, false));
        firingModesSwitcher.setCurrentText(currentAssault.getFiringMode());

        fullReloadSwitcher = findViewById(R.id.full_reload_value);
        fullReloadSwitcher.setFactory(new TextViewFactory(R.style.FullReloadSwitcher, false));
        fullReloadSwitcher.setCurrentText(currentAssault.getFullReloadTime());

        tacticalReloadSwitcher = findViewById(R.id.tactical_reload_value);
        tacticalReloadSwitcher.setFactory(new TextViewFactory(R.style.TacticalReloadSwitcher, false));
        tacticalReloadSwitcher.setCurrentText(currentAssault.getTacticalReloadTime());

        reloadMethodSwitcer = findViewById(R.id.reload_method_value);
        reloadMethodSwitcer.setFactory(new TextViewFactory(R.style.ReloadMethodSwitcher, false));
        reloadMethodSwitcer.setCurrentText(currentAssault.getReloadMethod());

        pickupSwitcher = findViewById(R.id.pickup_delay_value);
        pickupSwitcher.setFactory(new TextViewFactory(R.style.PickSwitcher, false));
        pickupSwitcher.setCurrentText(currentAssault.getPickupDelay());

        readySwitcher = findViewById(R.id.ready_delay_value);
        readySwitcher.setFactory(new TextViewFactory(R.style.ReadySwitcher, false));
        readySwitcher.setCurrentText(currentAssault.getReadyDelay());


        //progressbars
        hitProgress = findViewById(R.id.damageprogress);
        hitProgress.setProgress(currentAssault.hitDamage);

        bulletProgress = findViewById(R.id.bulletprogress);
        bulletProgress.setProgress(currentAssault.bulletSpeed);

        impactProgress = findViewById(R.id.impactprogress);
        impactProgress.setProgress(currentAssault.impactPower);

        rangeProgress = findViewById(R.id.rangeprogress);
        rangeProgress.setProgress(currentAssault.range);
    }

    private void hideOrShowAirDrop(String airDrop) {
        airDropLayout = findViewById(R.id.airdrop_layout);
        switch (airDrop) {
            case "yes":
                airDropLayout.setVisibility(View.VISIBLE);
                break;
            case "no":
                airDropLayout.setVisibility(View.GONE);
                break;
        }
    }

    private void initWeaponTitle() {
        weaponAnimDuration = getResources().getInteger(R.integer.labels_animation_duration);
        weaponOffset1 = getResources().getDimensionPixelSize(R.dimen.left_offset);
        weaponOffset2 = getResources().getDimensionPixelSize(R.dimen.card_width);
        weapon1TextView = (TextView) findViewById(R.id.tv_weapon_1);
        weapon2TextView = (TextView) findViewById(R.id.tv_weapon_2);

        weapon1TextView.setX(weaponOffset1);
        weapon2TextView.setX(weaponOffset2);
        weapon1TextView.setText(assaultsList.get(0).getWeaponName());
        weapon2TextView.setAlpha(0f);

        weapon1TextView.setTypeface(Typeface.createFromAsset(getAssets(), "open-sans-extrabold.ttf"));
        weapon2TextView.setTypeface(Typeface.createFromAsset(getAssets(), "open-sans-extrabold.ttf"));
    }


    private void setWeaponTitle(String text, boolean left2right) {
        final TextView invisibleText;
        final TextView visibleText;
        if (weapon1TextView.getAlpha() > weapon2TextView.getAlpha()) {
            visibleText = weapon1TextView;
            invisibleText = weapon2TextView;
        } else {
            visibleText = weapon2TextView;
            invisibleText = weapon1TextView;
        }

        final int vOffset;
        if (left2right) {
            invisibleText.setX(0);
            vOffset = weaponOffset2;
        } else {
            invisibleText.setX(weaponOffset2);
            vOffset = 0;
        }

        invisibleText.setText(text);

        final ObjectAnimator iAlpha = ObjectAnimator.ofFloat(invisibleText, "alpha", 1f);
        final ObjectAnimator vAlpha = ObjectAnimator.ofFloat(visibleText, "alpha", 0f);
        final ObjectAnimator iX = ObjectAnimator.ofFloat(invisibleText, "x", weaponOffset1);
        final ObjectAnimator vX = ObjectAnimator.ofFloat(visibleText, "x", vOffset);

        final AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(iAlpha, vAlpha, iX, vX);
        animSet.setDuration(weaponAnimDuration);
        animSet.start();
    }

    private void onActiveCardChange() {
        final int pos = layoutManger.getActiveCardPosition();
        if (pos == RecyclerView.NO_POSITION || pos == currentPosition) {
            return;
        }

        onActiveCardChange(pos);
    }

    private void onActiveCardChange(int pos) {
        AssaultsAdapter assaultInPosition = assaultsList.get(pos % assaultsList.size());

        int animH[] = new int[]{R.anim.slide_in_right, R.anim.slide_out_left};
        int animV[] = new int[]{R.anim.slide_in_top, R.anim.slide_out_bottom};

        final boolean left2right = pos < currentPosition;
        if (left2right) {
            animH[0] = R.anim.slide_in_left;
            animH[1] = R.anim.slide_out_right;

            animV[0] = R.anim.slide_in_bottom;
            animV[1] = R.anim.slide_out_top;
        }

        setWeaponTitle(assaultInPosition.getWeaponName(), left2right);

        hitProgress.setProgress(assaultInPosition.getHitDamage());
        Log.d("Hit Damage", String.valueOf(assaultInPosition.getRange() * 100 / 120));
        bulletProgress.setProgress(assaultInPosition.getBulletSpeed());
        impactProgress.setProgress(assaultInPosition.getImpactPower());
        rangeProgress.setProgress(assaultInPosition.getRange());

        ammoSwitcher.setInAnimation(WeaponsActivity.this, animH[0]);
        ammoSwitcher.setOutAnimation(WeaponsActivity.this, animH[1]);
        ammoSwitcher.setText(assaultInPosition.getAmmo());

        descriptionSwitcher.setText(assaultInPosition.getWeaponDesc());

        hideOrShowAirDrop(assaultInPosition.getAirDrop());

        magazineSizeSwitcher.setInAnimation(this, animV[0]);
        magazineSizeSwitcher.setOutAnimation(this, animV[1]);
        magazineSizeSwitcher.setText(assaultInPosition.getMagazineSize());

        timeBnShotsSwitcher.setInAnimation(this, animV[0]);
        timeBnShotsSwitcher.setOutAnimation(this, animV[1]);
        timeBnShotsSwitcher.setText(assaultInPosition.getTimeBnShots());

        firingModesSwitcher.setInAnimation(this, animH[0]);
        firingModesSwitcher.setOutAnimation(this, animH[1]);
        firingModesSwitcher.setText(assaultInPosition.getFiringMode());

        fullReloadSwitcher.setInAnimation(this, animV[0]);
        fullReloadSwitcher.setOutAnimation(this, animV[1]);
        fullReloadSwitcher.setText(assaultInPosition.getFullReloadTime());

        tacticalReloadSwitcher.setInAnimation(this, animV[0]);
        tacticalReloadSwitcher.setOutAnimation(this, animV[1]);
        tacticalReloadSwitcher.setText(assaultInPosition.getTacticalReloadTime());

        reloadMethodSwitcer.setInAnimation(this, animH[0]);
        reloadMethodSwitcer.setOutAnimation(this, animH[1]);
        reloadMethodSwitcer.setText(assaultInPosition.getReloadMethod());

        pickupSwitcher.setInAnimation(this, animV[0]);
        pickupSwitcher.setOutAnimation(this, animV[1]);
        pickupSwitcher.setText(assaultInPosition.getPickupDelay());

        readySwitcher.setInAnimation(this, animV[0]);
        readySwitcher.setOutAnimation(this, animV[1]);
        readySwitcher.setText(assaultInPosition.getReadyDelay());

        currentPosition = pos;
    }


    private class TextViewFactory implements ViewSwitcher.ViewFactory {

        @StyleRes
        final int styleId;
        final boolean center;

        TextViewFactory(@StyleRes int styleId, boolean center) {
            this.styleId = styleId;
            this.center = center;
        }

        @SuppressWarnings("deprecation")
        @Override
        public View makeView() {
            final TextView textView = new TextView(WeaponsActivity.this);

            if (center) {
                textView.setGravity(Gravity.CENTER);
            }

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                textView.setTextAppearance(WeaponsActivity.this, styleId);
            } else {
                textView.setTextAppearance(styleId);
            }

            return textView;
        }

    }

    private class ImageViewFactory implements ViewSwitcher.ViewFactory {
        @Override
        public View makeView() {
            final ImageView imageView = new ImageView(WeaponsActivity.this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            final LayoutParams lp = new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(lp);

            return imageView;
        }
    }

    private class OnCardClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            final CardSliderLayoutManager lm = (CardSliderLayoutManager) recyclerView.getLayoutManager();

            if (lm.isSmoothScrolling()) {
                return;
            }

            final int activeCardPosition = lm.getActiveCardPosition();
            if (activeCardPosition == RecyclerView.NO_POSITION) {
                return;
            }

            final int clickedPosition = recyclerView.getChildAdapterPosition(view);
            if (clickedPosition == activeCardPosition) {
                final Intent intent = new Intent(WeaponsActivity.this, DetailsActivity.class);
                intent.putExtra(DetailsActivity.BUNDLE_IMAGE_ID, imageUrls[activeCardPosition % imageUrls.length]);

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent);
                } else {
                    final CardView cardView = (CardView) view;
                    final View sharedView = cardView.getChildAt(cardView.getChildCount() - 1);
                    final ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation(WeaponsActivity.this, sharedView, "shared");
                    startActivity(intent, options.toBundle());
                }
            } else if (clickedPosition > activeCardPosition) {
                recyclerView.smoothScrollToPosition(clickedPosition);
                onActiveCardChange(clickedPosition);
            }
        }
    }

}
