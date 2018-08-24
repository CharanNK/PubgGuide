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

import com.charanajayworks.pubgguide.Adapters.AttachmentsAdapter;
import com.charanajayworks.pubgguide.cards.SliderAdapter;
import com.ramotion.cardslider.CardSliderLayoutManager;
import com.ramotion.cardslider.CardSnapHelper;

import java.util.ArrayList;

public class AttachmentsActivity extends AppCompatActivity {

    ArrayList<AttachmentsAdapter> attachmentsList;

    private String[] imageUrls = {"https://i.imgur.com/U4T1O47.png", "https://i.imgur.com/n38pVbH.png", "https://i.imgur.com/0SIZrf9.png", "https://i.imgur.com/gcYkveG.png"};

    private final SliderAdapter sliderAdapter = new SliderAdapter(imageUrls, 20, new OnCardClickListener());

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
    private TextSwitcher attachableSwitcher;
    private TextSwitcher attributesSwitcher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.attachment_activity);

        attachmentsList = new ArrayList<>();

        attachmentsList.add(new AttachmentsAdapter("15x PM II Scope","https://i.imgur.com/U4T1O47.png","12.00x Magnification** **Contrary to its claimed 15x magnification power, the 15x PM II Scope actually only zooms in by 12x. Nonetheless, this telescopic sight is a variable scope (You can zoom in/out your magnification) that allows for extreme range sharpshooting, and thus is best used with weapons that have a high bullet velocity in order to reduce the need to compensate for both gravity and bullet travel time. As such, the Mini-14 works well since it has the fastest initial bullet speed of any gun in the game, surpassing even the powerful AWM(Although the Mini loses its speed much faster) . Due to its very strong zoom, scope sway is also drastically exaggerated, requiring the user to hold their breath before every shot if engaging targets at extreme distances. The strong zoom also necessitates keeping a spare lower power sight in the inventory for closer range encounters.","SKS, S12K, M249, Kar98k, M24, AWM, Mk14 EBR, Mini 14, SLR, QBU","20","-"));
        attachmentsList.add(new AttachmentsAdapter("2x Aimpoint Scope","https://i.imgur.com/n38pVbH.png","1.80x Magnification +10.00% Faster ADS","UMP9, AKM, M16A4, M416, SCAR-L, SKS, S12K, M249, Kar98k, M24, AWM, KRISS Vector, OTs-14 Groza, Mk14 EBR, Mini 14, DP-28, AUG A3, SLR, QBZ95, QBU","15","-"));
        attachmentsList.add(new AttachmentsAdapter("15x PM II Scope","https://i.imgur.com/U4T1O47.png","12.00x Magnification** **Contrary to its claimed 15x magnification power, the 15x PM II Scope actually only zooms in by 12x. Nonetheless, this telescopic sight is a variable scope (You can zoom in/out your magnification) that allows for extreme range sharpshooting, and thus is best used with weapons that have a high bullet velocity in order to reduce the need to compensate for both gravity and bullet travel time. As such, the Mini-14 works well since it has the fastest initial bullet speed of any gun in the game, surpassing even the powerful AWM(Although the Mini loses its speed much faster) . Due to its very strong zoom, scope sway is also drastically exaggerated, requiring the user to hold their breath before every shot if engaging targets at extreme distances. The strong zoom also necessitates keeping a spare lower power sight in the inventory for closer range encounters.","SKS, S12K, M249, Kar98k, M24, AWM, Mk14 EBR, Mini 14, SLR, QBU","20","-"));
        attachmentsList.add(new AttachmentsAdapter("15x PM II Scope","https://i.imgur.com/U4T1O47.png","12.00x Magnification** **Contrary to its claimed 15x magnification power, the 15x PM II Scope actually only zooms in by 12x. Nonetheless, this telescopic sight is a variable scope (You can zoom in/out your magnification) that allows for extreme range sharpshooting, and thus is best used with weapons that have a high bullet velocity in order to reduce the need to compensate for both gravity and bullet travel time. As such, the Mini-14 works well since it has the fastest initial bullet speed of any gun in the game, surpassing even the powerful AWM(Although the Mini loses its speed much faster) . Due to its very strong zoom, scope sway is also drastically exaggerated, requiring the user to hold their breath before every shot if engaging targets at extreme distances. The strong zoom also necessitates keeping a spare lower power sight in the inventory for closer range encounters.","SKS, S12K, M249, Kar98k, M24, AWM, Mk14 EBR, Mini 14, SLR, QBU","20","-"));

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

    private void initWeaponTitle() {
        weaponAnimDuration = getResources().getInteger(R.integer.labels_animation_duration);
        weaponOffset1 = getResources().getDimensionPixelSize(R.dimen.left_offset);
        weaponOffset2 = getResources().getDimensionPixelSize(R.dimen.card_width);
        weapon1TextView = (TextView) findViewById(R.id.tv_weapon_1);
        weapon2TextView = (TextView) findViewById(R.id.tv_weapon_2);

        weapon1TextView.setX(weaponOffset1);
        weapon2TextView.setX(weaponOffset2);
        weapon1TextView.setText(attachmentsList.get(0).getAttachmentName());
        weapon2TextView.setAlpha(0f);

        weapon1TextView.setTypeface(Typeface.createFromAsset(getAssets(), "open-sans-extrabold.ttf"));
        weapon2TextView.setTypeface(Typeface.createFromAsset(getAssets(), "open-sans-extrabold.ttf"));
    }

    private void onActiveCardChange() {
        final int pos = layoutManger.getActiveCardPosition();
        if (pos == RecyclerView.NO_POSITION || pos == currentPosition) {
            return;
        }

        onActiveCardChange(pos);
    }

    private void initSwitchers() {
        AttachmentsAdapter currentAttachment = attachmentsList.get(0);


        descriptionSwitcher = findViewById(R.id.weapon_description);
        descriptionSwitcher.setFactory(new TextViewFactory(R.style.WeaponDescriptionTextView, false));
        descriptionSwitcher.setInAnimation(this, R.anim.fade_in);
        descriptionSwitcher.setOutAnimation(this, R.anim.fade_out);
        descriptionSwitcher.setCurrentText(currentAttachment.getAttachmentDescription());

        capacitySwitcher = findViewById(R.id.attachable_capacity_value);
        capacitySwitcher.setFactory(new TextViewFactory(R.style.ReadySwitcher,false));
        capacitySwitcher.setText(currentAttachment.getCapacity());

        attachableSwitcher = findViewById(R.id.attachable_weapons_value);
        attachableSwitcher.setFactory(new TextViewFactory(R.style.FullReloadSwitcher,false));
        attachableSwitcher.setInAnimation(this,R.anim.fade_in);
        attachableSwitcher.setOutAnimation(this,R.anim.fade_out);
        attachableSwitcher.setText(currentAttachment.getAttachableWeapons());

        attributesSwitcher = findViewById(R.id.attachable_attributes_value);
        attributesSwitcher.setFactory(new TextViewFactory(R.style.FiringModeSwitcher,false));
        attributesSwitcher.setInAnimation(this,R.anim.fade_in);
        attributesSwitcher.setOutAnimation(this,R.anim.fade_out);
        attributesSwitcher.setText(currentAttachment.getAttributes());
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

    private void onActiveCardChange(int pos) {
        AttachmentsAdapter attachmentInPosition = attachmentsList.get(pos % attachmentsList.size());

        int animH[] = new int[]{R.anim.slide_in_right, R.anim.slide_out_left};
        int animV[] = new int[]{R.anim.slide_in_top, R.anim.slide_out_bottom};

        final boolean left2right = pos < currentPosition;
        if (left2right) {
            animH[0] = R.anim.slide_in_left;
            animH[1] = R.anim.slide_out_right;

            animV[0] = R.anim.slide_in_bottom;
            animV[1] = R.anim.slide_out_top;
        }

        setWeaponTitle(attachmentInPosition.getAttachmentName(), left2right);

        descriptionSwitcher.setText(attachmentInPosition.getAttachmentDescription());
        capacitySwitcher.setText(attachmentInPosition.getCapacity());
        attachableSwitcher.setText(attachmentInPosition.getAttachableWeapons());
        attributesSwitcher.setText(attachmentInPosition.getAttributes());

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
            final TextView textView = new TextView(AttachmentsActivity.this);

            if (center) {
                textView.setGravity(Gravity.CENTER);
            }

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                textView.setTextAppearance(AttachmentsActivity.this, styleId);
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
                final Intent intent = new Intent(AttachmentsActivity.this, DetailsActivity.class);
                intent.putExtra(DetailsActivity.BUNDLE_IMAGE_ID, imageUrls[activeCardPosition % imageUrls.length]);

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent);
                } else {
                    final CardView cardView = (CardView) view;
                    final View sharedView = cardView.getChildAt(cardView.getChildCount() - 1);
                    final ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation(AttachmentsActivity.this, sharedView, "shared");
                    startActivity(intent, options.toBundle());
                }
            } else if (clickedPosition > activeCardPosition) {
                recyclerView.smoothScrollToPosition(clickedPosition);
                onActiveCardChange(clickedPosition);
            }
        }
    }
}
