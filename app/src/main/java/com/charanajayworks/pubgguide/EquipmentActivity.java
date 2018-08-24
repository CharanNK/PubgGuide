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

import com.charanajayworks.pubgguide.Adapters.EquipmentAdapter;
import com.charanajayworks.pubgguide.cards.SliderAdapter;
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;

import java.util.ArrayList;

public class EquipmentActivity extends AppCompatActivity {
    ArrayList<EquipmentAdapter> equipmentsList;

    private final String[] imageUrls = {"https://i.imgur.com/gyHbJRp.png", "https://i.imgur.com/34ifn4M.png", "https://i.imgur.com/WU0RdHW.png", "https://i.imgur.com/cctbksj.png"};

    private final SliderAdapter sliderAdapter = new SliderAdapter(imageUrls, imageUrls.length, new OnCardClickListener());

    private CardSliderLayoutManager layoutManger;
    private RecyclerView recyclerView;

    private TextView weapon1TextView;
    private TextView weapon2TextView;
    private int weaponOffset1;
    private int weaponOffset2;
    private long weaponAnimDuration;
    private int currentPosition;

    private TextSwitcher descriptionSwitcher;
    private TextSwitcher capacitySwitcher;
    private TextSwitcher damageSwitcher;
    private TextSwitcher durabilitySwitcher;
    private TextSwitcher weightSwitcher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.equipment_activity);

        equipmentsList = new ArrayList<>();

        equipmentsList.add(new EquipmentAdapter("Motorcycle Helmet (Level 1)","https://i.imgur.com/gyHbJRp.png","-","0.3","80","40"));
        equipmentsList.add(new EquipmentAdapter("Military Helmet (Level 2)","https://i.imgur.com/34ifn4M.png","-","0.4","150","50"));
        equipmentsList.add(new EquipmentAdapter("Spetsnaz Helmet (Level 3)","https://i.imgur.com/WU0RdHW.png","-","0.55","230","60"));
        equipmentsList.add(new EquipmentAdapter("Police Vest (Level 1)","https://i.imgur.com/cctbksj.png","50","0.3","200","120"));

        initRecyclerView();
        initSwitchers();
        initWeaponTitle();
    }

    private void initSwitchers() {
        EquipmentAdapter currentEquipment = equipmentsList.get(0);

//        descriptionSwitcher = findViewById(R.id.weapon_description);
//        descriptionSwitcher.setFactory(new TextViewFactory(R.style.WeaponDescriptionTextView, true));
//        descriptionSwitcher.setInAnimation(this, R.anim.fade_in);
//        descriptionSwitcher.setOutAnimation(this, R.anim.fade_out);
//        descriptionSwitcher.setText(currentEquipment.getEquipmentDesc());

        capacitySwitcher = findViewById(R.id.capacity_value);
        capacitySwitcher.setFactory(new TextViewFactory(R.style.ReadySwitcher,false));
        capacitySwitcher.setText(currentEquipment.getCapacity());

        damageSwitcher = findViewById(R.id.damage_value);
        damageSwitcher.setFactory(new TextViewFactory(R.style.PickSwitcher,false));
        damageSwitcher.setText(currentEquipment.getDamage());

        durabilitySwitcher = findViewById(R.id.durability_value);
        durabilitySwitcher.setFactory(new TextViewFactory(R.style.FullReloadSwitcher,false));
        durabilitySwitcher.setText(currentEquipment.getDurability());

        weightSwitcher = findViewById(R.id.weight_value);
        weightSwitcher.setFactory(new TextViewFactory(R.style.FiringModeSwitcher,false));
        weightSwitcher.setText(currentEquipment.getWeight());
    }

    private void initWeaponTitle() {
        weaponAnimDuration = getResources().getInteger(R.integer.labels_animation_duration);
        weaponOffset1 = getResources().getDimensionPixelSize(R.dimen.left_offset);
        weaponOffset2 = getResources().getDimensionPixelSize(R.dimen.card_width);
        weaponAnimDuration = getResources().getInteger(R.integer.labels_animation_duration);
        weaponOffset1 = getResources().getDimensionPixelSize(R.dimen.left_offset);
        weaponOffset2 = getResources().getDimensionPixelSize(R.dimen.card_width);
        weapon1TextView = (TextView) findViewById(R.id.tv_weapon_1);
        weapon2TextView = (TextView) findViewById(R.id.tv_weapon_2);

        weapon1TextView.setX(weaponOffset1);
        weapon2TextView.setX(weaponOffset2);
        weapon1TextView.setText(equipmentsList.get(0).getEquipmentName());
        weapon2TextView.setAlpha(0f);

        weapon1TextView.setTypeface(Typeface.createFromAsset(getAssets(), "open-sans-extrabold.ttf"));
        weapon2TextView.setTypeface(Typeface.createFromAsset(getAssets(), "open-sans-extrabold.ttf"));
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
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

    private void onActiveCardChange() {
        final int pos = layoutManger.getActiveCardPosition();
        if (pos == RecyclerView.NO_POSITION || pos == currentPosition) {
            return;
        }

        onActiveCardChange(pos);
    }

    private void onActiveCardChange(int pos) {
        EquipmentAdapter equipmentInPosition = equipmentsList.get(pos % equipmentsList.size());

        int animH[] = new int[]{R.anim.slide_in_right, R.anim.slide_out_left};
        int animV[] = new int[]{R.anim.slide_in_top, R.anim.slide_out_bottom};

        final boolean left2right = pos < currentPosition;
        if (left2right) {
            animH[0] = R.anim.slide_in_left;
            animH[1] = R.anim.slide_out_right;

            animV[0] = R.anim.slide_in_bottom;
            animV[1] = R.anim.slide_out_top;
        }

        setWeaponTitle(equipmentInPosition.getEquipmentName(), left2right);

//        descriptionSwitcher.setText(equipmentInPosition.getEquipmentDesc());

        capacitySwitcher.setInAnimation(this,animV[0]);
        capacitySwitcher.setOutAnimation(this,animV[1]);
        capacitySwitcher.setText(equipmentInPosition.getCapacity());

        damageSwitcher.setInAnimation(this,animV[0]);
        damageSwitcher.setOutAnimation(this,animV[1]);
        damageSwitcher.setText(equipmentInPosition.getDamage());

        durabilitySwitcher.setInAnimation(this,animV[0]);
        durabilitySwitcher.setOutAnimation(this,animV[1]);
        durabilitySwitcher.setText(equipmentInPosition.getDurability());

        weightSwitcher.setInAnimation(this,animV[0]);
        weightSwitcher.setOutAnimation(this,animV[1]);
        weightSwitcher.setText(equipmentInPosition.getWeight());

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
            final TextView textView = new TextView(EquipmentActivity.this);

            if (center) {
                textView.setGravity(Gravity.CENTER);
            }

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                textView.setTextAppearance(EquipmentActivity.this, styleId);
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
                final Intent intent = new Intent(EquipmentActivity.this, DetailsActivity.class);
                intent.putExtra(DetailsActivity.BUNDLE_IMAGE_ID, imageUrls[activeCardPosition % imageUrls.length]);

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent);
                } else {
                    final CardView cardView = (CardView) view;
                    final View sharedView = cardView.getChildAt(cardView.getChildCount() - 1);
                    final ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation(EquipmentActivity.this, sharedView, "shared");
                    startActivity(intent, options.toBundle());
                }
            } else if (clickedPosition > activeCardPosition) {
                recyclerView.smoothScrollToPosition(clickedPosition);
                onActiveCardChange(clickedPosition);
            }
        }
    }
}
