package com.charanajayworks.pubgguide;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.charanajayworks.pubgguide.Adapters.ThrowablesAdapter;
import com.charanajayworks.pubgguide.cards.SliderAdapter;
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;

import java.util.ArrayList;

public class ThrowablesActivity extends AppCompatActivity {


    ArrayList<ThrowablesAdapter> throwablesList;
    private final String[] imageUrls = {"https://i.imgur.com/PEVJRyw.png", "https://i.imgur.com/GsUEnu9.png", "https://i.imgur.com/hlNRGRI.png","https://i.imgur.com/lFlz7o4.png"};

    private final SliderAdapter sliderAdapter = new SliderAdapter(imageUrls, imageUrls.length, new OnCardClickListener());

    private CardSliderLayoutManager layoutManger;
    private RecyclerView recyclerView;

    private TextView weapon1TextView;
    private TextView weapon2TextView;
    private int weaponOffset1;
    private int weaponOffset2;
    private long weaponAnimDuration;
    private int currentPosition;

    private TextSwitcher throwTimeSwitcher;
    private TextSwitcher descriptionSwitcher;
    private TextSwitcher coolDownSwitcher;
    private TextSwitcher fireDelaySwitcher;
    private TextSwitcher activationTimeSwitcher;
    private TextSwitcher explosionDelaySwitcher;
    private TextSwitcher detonationTypeSwitcher;
    private TextSwitcher pickUpDelaySwitcher;
    private TextSwitcher readyDelaySwitcher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.throwables_activity);

        throwablesList = new ArrayList<>();

        throwablesList.add(new ThrowablesAdapter("FRAG GRENADE", "https://i.imgur.com/PEVJRyw.png", "Fire in the hole.", "1.3 s", "1 s", "0.4 s", "0 s", "DELAY", "5 s", "0.15 s", "1 s"));
        throwablesList.add(new ThrowablesAdapter("SMOKE GRENADE", "https://i.imgur.com/GsUEnu9.png", "Smokin' useful.", "1.3 s", "1 s", "0.4 s", "0 s", "DELAY", "5 s", "0.15 s", "1 s"));
        throwablesList.add(new ThrowablesAdapter("FRAG GRENADE", "https://i.imgur.com/PEVJRyw.png", "Fire in the hole.", "1.3 s", "1 s", "0.4 s", "0 s", "DELAY", "5 s", "0.15 s", "1 s"));
        throwablesList.add(new ThrowablesAdapter("FRAG GRENADE", "https://i.imgur.com/PEVJRyw.png", "Fire in the hole.", "1.3 s", "1 s", "0.4 s", "0 s", "DELAY", "5 s", "0.15 s", "1 s"));

        initRecyclerView();
        initWeaponTitle();
        initSwitchers();
    }

    private void initSwitchers() {
        ThrowablesAdapter currentThrowable = throwablesList.get(0);

        descriptionSwitcher = findViewById(R.id.throwable_description);
        descriptionSwitcher.setFactory(new ThrowablesActivity.TextViewFactory(R.style.WeaponDescriptionTextView, true));
        descriptionSwitcher.setInAnimation(this,R.anim.fade_in);
        descriptionSwitcher.setOutAnimation(this,R.anim.fade_out);
        descriptionSwitcher.setText(currentThrowable.getThrowableDesc());

        throwTimeSwitcher = findViewById(R.id.throw_time_value);
        throwTimeSwitcher.setFactory(new ThrowablesActivity.TextViewFactory(R.style.TimeBnSwitcher,false));
        throwTimeSwitcher.setText(currentThrowable.getThrowTime());

        coolDownSwitcher = findViewById(R.id.throw_cool_down_value);
        coolDownSwitcher.setFactory(new ThrowablesActivity.TextViewFactory(R.style.MagazineSizeSwitcher,false));
        coolDownSwitcher.setText(currentThrowable.getThrowCoolDown());

        fireDelaySwitcher = findViewById(R.id.fire_delay_value);
        fireDelaySwitcher.setFactory(new ThrowablesActivity.TextViewFactory(R.style.FullReloadSwitcher,false));
        fireDelaySwitcher.setText(currentThrowable.getFireDelay());

        activationTimeSwitcher = findViewById(R.id.activation_time_value);
        activationTimeSwitcher.setFactory(new ThrowablesActivity.TextViewFactory(R.style.TacticalReloadSwitcher,false));
        activationTimeSwitcher.setText(currentThrowable.getActivationTime());

        explosionDelaySwitcher = findViewById(R.id.explosion_delay_value);
        explosionDelaySwitcher.setFactory(new ThrowablesActivity.TextViewFactory(R.style.ExplosionDelaySwitcher,false));
        explosionDelaySwitcher.setText(currentThrowable.getExplosionDelay());

        detonationTypeSwitcher = findViewById(R.id.detonation_value);
        detonationTypeSwitcher.setFactory(new ThrowablesActivity.TextViewFactory(R.style.FiringModeSwitcher,false));
        detonationTypeSwitcher.setText(currentThrowable.getDetonationMode());

        pickUpDelaySwitcher = findViewById(R.id.pickup_delay_value);
        pickUpDelaySwitcher.setFactory(new ThrowablesActivity.TextViewFactory(R.style.PickSwitcher,false));
        pickUpDelaySwitcher.setText(currentThrowable.getPickupDelay());

        readyDelaySwitcher = findViewById(R.id.ready_delay_value);
        readyDelaySwitcher.setFactory(new ThrowablesActivity.TextViewFactory(R.style.ReadySwitcher,false));
        readyDelaySwitcher.setText(currentThrowable.getReadyDelay());
    }

    private void initWeaponTitle() {
        weaponAnimDuration = getResources().getInteger(R.integer.labels_animation_duration);
        weaponOffset1 = getResources().getDimensionPixelSize(R.dimen.left_offset);
        weaponOffset2 = getResources().getDimensionPixelSize(R.dimen.card_width);
        weaponAnimDuration = getResources().getInteger(R.integer.labels_animation_duration);
        weaponOffset1 = getResources().getDimensionPixelSize(R.dimen.left_offset);
        weaponOffset2 = getResources().getDimensionPixelSize(R.dimen.card_width);
        weapon1TextView = (TextView) findViewById(R.id.throwable_title1);
        weapon2TextView = (TextView) findViewById(R.id.throwable_title2);

        weapon1TextView.setX(weaponOffset1);
        weapon2TextView.setX(weaponOffset2);
        weapon1TextView.setText(throwablesList.get(0).getThrowablename());
        weapon2TextView.setAlpha(0f);

        weapon1TextView.setTypeface(Typeface.createFromAsset(getAssets(), "open-sans-extrabold.ttf"));
        weapon2TextView.setTypeface(Typeface.createFromAsset(getAssets(), "open-sans-extrabold.ttf"));
    }

    private void initRecyclerView() {
        recyclerView =  findViewById(R.id.throwable_recycler_view);
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

    private void onActiveCardChange() {
        final int pos = layoutManger.getActiveCardPosition();
        if (pos == RecyclerView.NO_POSITION || pos == currentPosition) {
            return;
        }

        onActiveCardChange(pos);
    }

    private void onActiveCardChange(int pos) {
        ThrowablesAdapter throwableInPosition = throwablesList.get(pos%throwablesList.size());

        int animH[] = new int[]{R.anim.slide_in_right, R.anim.slide_out_left};
        int animV[] = new int[]{R.anim.slide_in_top, R.anim.slide_out_bottom};

        final boolean left2right = pos < currentPosition;
        if (left2right) {
            animH[0] = R.anim.slide_in_left;
            animH[1] = R.anim.slide_out_right;

            animV[0] = R.anim.slide_in_bottom;
            animV[1] = R.anim.slide_out_top;
        }

        setWeaponTitle(throwableInPosition.getThrowablename(), left2right);

        descriptionSwitcher.setText(throwableInPosition.getThrowableDesc());

        throwTimeSwitcher.setInAnimation(this,animV[0]);
        throwTimeSwitcher.setOutAnimation(this,animV[1]);
        throwTimeSwitcher.setText(throwableInPosition.getThrowTime());

        coolDownSwitcher.setInAnimation(this,animV[0]);
        coolDownSwitcher.setOutAnimation(this,animV[1]);
        coolDownSwitcher.setText(throwableInPosition.getThrowCoolDown());

        fireDelaySwitcher.setInAnimation(this, animV[0]);
        fireDelaySwitcher.setOutAnimation(this,animV[1]);
        fireDelaySwitcher.setText(throwableInPosition.getFireDelay());

        activationTimeSwitcher.setInAnimation(this,animV[0]);
        activationTimeSwitcher.setOutAnimation(this,animV[1]);
        activationTimeSwitcher.setText(throwableInPosition.getActivationTime());

        explosionDelaySwitcher.setInAnimation(this,animV[0]);
        explosionDelaySwitcher.setOutAnimation(this,animV[1]);
        explosionDelaySwitcher.setText(throwableInPosition.getExplosionDelay());

        detonationTypeSwitcher.setInAnimation(this, animH[0]);
        detonationTypeSwitcher.setOutAnimation(this,animH[1]);
        detonationTypeSwitcher.setText(throwableInPosition.getDetonationMode());

        pickUpDelaySwitcher.setInAnimation(this,animV[0]);
        pickUpDelaySwitcher.setOutAnimation(this,animV[1]);
        pickUpDelaySwitcher.setText(throwableInPosition.getPickupDelay());

        readyDelaySwitcher.setInAnimation(this,animV[0]);
        readyDelaySwitcher.setOutAnimation(this,animV[1]);
        readyDelaySwitcher.setText(throwableInPosition.getReadyDelay());

        currentPosition = pos;
    }

    private void setWeaponTitle(String throwablename, boolean left2right) {
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

        invisibleText.setText(throwablename);

        final ObjectAnimator iAlpha = ObjectAnimator.ofFloat(invisibleText, "alpha", 1f);
        final ObjectAnimator vAlpha = ObjectAnimator.ofFloat(visibleText, "alpha", 0f);
        final ObjectAnimator iX = ObjectAnimator.ofFloat(invisibleText, "x", weaponOffset1);
        final ObjectAnimator vX = ObjectAnimator.ofFloat(visibleText, "x", vOffset);

        final AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(iAlpha, vAlpha, iX, vX);
        animSet.setDuration(weaponAnimDuration);
        animSet.start();
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
            final TextView textView = new TextView(ThrowablesActivity.this);

            if (center) {
                textView.setGravity(Gravity.CENTER);
            }

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                textView.setTextAppearance(ThrowablesActivity.this, styleId);
            } else {
                textView.setTextAppearance(styleId);
            }

            return textView;
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
                final Intent intent = new Intent(ThrowablesActivity.this, DetailsActivity.class);
                intent.putExtra(DetailsActivity.BUNDLE_IMAGE_ID, imageUrls[activeCardPosition % imageUrls.length]);

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent);
                } else {
                    final CardView cardView = (CardView) view;
                    final View sharedView = cardView.getChildAt(cardView.getChildCount() - 1);
                    final ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation(ThrowablesActivity.this, sharedView, "shared");
                    startActivity(intent, options.toBundle());
                }
            } else if (clickedPosition > activeCardPosition) {
                recyclerView.smoothScrollToPosition(clickedPosition);
                onActiveCardChange(clickedPosition);
            }
        }
    }
}


