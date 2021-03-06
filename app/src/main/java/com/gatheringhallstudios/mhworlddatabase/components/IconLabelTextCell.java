package com.gatheringhallstudios.mhworlddatabase.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gatheringhallstudios.mhworlddatabase.R;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This is a full height, full width cell that displays an icon, label, and value. Used to generate
 * data rows in RecyclerView or inside XML layouts.
 */

public class IconLabelTextCell extends ConstraintLayout{

    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.generic_icon) ImageView imageView;
    @BindView(R.id.label_text) TextView labelView;
    @BindView(R.id.value_text) TextView valueView;

    public IconLabelTextCell (Context context, @DrawableRes int imgSrc, String labelText, String valueText) {
        super(context);
        Drawable drawable = AppCompatResources.getDrawable(getContext(), imgSrc);
        init(drawable, labelText, valueText);
    }

    public IconLabelTextCell(Context context) {
        super(context);
        init(null, "", "");
    }

    public IconLabelTextCell(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.IconLabelTextCell);

        // Set values from attributes
        Drawable drawable;
        String labelText;
        String valueText;
        try {
            drawable = attributes.getDrawable(R.styleable.IconLabelTextCell_iconSrc);
            labelText = attributes.getString(R.styleable.IconLabelTextCell_labelText);
            valueText = attributes.getString(R.styleable.IconLabelTextCell_valueText);
        } finally {
            // Typed arrays should be recycled after use
            attributes.recycle();
        }

        init(drawable, labelText, valueText);
    }

    public void init(Drawable drawable, String labelText, String valueText) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.cell_icon_label_text, this, true);

        ButterKnife.bind(this);

        setLeftIconDrawable(drawable);
        setLabelText(labelText);
        setValueText(valueText);

        setLayoutParams(new ViewGroup.LayoutParams(
                LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        // makes this cell have touch feedback...since the xml is a merge tag, we can't do this in xml
        TypedValue highlightBackground = new TypedValue();
        getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, highlightBackground, true);
        setBackgroundResource(highlightBackground.resourceId);
    }

    /**
     * Set custom drawable for the left icon
     */
    public void setLeftIconDrawable(Drawable drawable) {
        imageView.setImageDrawable(drawable);

        // Invalidate to trigger layout update
        invalidate();
    }

    public void setLeftIconType(IconType type) {
        IconTypeFn.applyIconType(imageView, type);
    }

    public CharSequence getLabelText() {
        return labelView.getText();
    }

    public void setLabelText(String labelText) {
        labelView.setText(labelText);
    }

    public void setValueText(String valueText) {
        valueView.setText(valueText);
    }

    /**
     * Removes the decorator around the icon.
     * TODO Make this an instantiation flag if necessary
     */
    public void removeDecorator() {
        imageView.setBackground(null);
        imageView.setPadding(0, 0, 0, 0);
    }
}
