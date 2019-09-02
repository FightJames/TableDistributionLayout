package com.numbertech.tabledistributionlayoutlib

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup

class TableDistributionLayout : ViewGroup {

    var columnCount = 3
    var itemSpaceWidth = 0
    var itemSpaceHeight = 0
    protected var mode = Mode.FIXED

    enum class Mode {
        FIXED, VARIATION
    }

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs, 0) {
        val attribute = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.NumberTechTableDistribution,
            0, 0
        )

        columnCount = attribute.getInteger(R.styleable.NumberTechTableDistribution_columnCount, 0)
        mode = if (columnCount < 1) Mode.VARIATION else Mode.FIXED
        itemSpaceWidth =
            attribute.getDimension(R.styleable.NumberTechTableDistribution_itemSpaceWidth, 0f)
                .toInt()
        itemSpaceHeight =
            attribute.getDimension(R.styleable.NumberTechTableDistribution_itemSpaceHeight, 0f)
                .toInt()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var widthMode = MeasureSpec.getMode(widthMeasureSpec);
        var heightMode = MeasureSpec.getMode(heightMeasureSpec);
        var sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        var sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        when {
            (mode == Mode.VARIATION) -> {
                Log.d("parent ", "wrap")
                //Todo must implement Mode.VARIATION measure
                super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            }
            else -> {
                var eachWidth = (sizeWidth - (itemSpaceWidth) * (columnCount - 1)) / columnCount
                for (i in 0 until childCount) {
                    var childView = getChildAt(i)
                    childView.layoutParams.width = eachWidth
                }
                //calculate all child view height and width
                measureChildren(widthMeasureSpec, heightMeasureSpec);
                var height = 0
                var eachLineMaxHeight = 0
                for (i in 0 until childCount) {
                    var childView = getChildAt(i)
                    eachLineMaxHeight = Math.max(childView.measuredHeight, eachLineMaxHeight)
                    if (i % columnCount == (columnCount - 1)) {
                        height += eachLineMaxHeight + itemSpaceHeight
                        eachLineMaxHeight = 0;
                    }
                }
                Log.d("height ", height.toString() + "  " + sizeHeight.toString())
                Log.d("height mode ", (heightMode == MeasureSpec.EXACTLY).toString())
                setMeasuredDimension(sizeWidth, height)
            }
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        Log.d("top", " $t")
        var occupancyWidth = 0
        var occupancyHeight = 0
        var eachLineMaxHeight = 0
        when (mode) {
            Mode.FIXED -> {
                for (i in 0 until childCount) {
                    var childView = getChildAt(i)
                    var childWidth = childView.measuredWidth
                    var childHeight = childView.measuredHeight
                    var childParams = childView.layoutParams as ViewGroup.MarginLayoutParams
                    eachLineMaxHeight = Math.max(childHeight, eachLineMaxHeight)

                    if (i % columnCount == (columnCount - 1)) {
                        childView.layout(
                            occupancyWidth + childParams.leftMargin,
                            occupancyHeight + childParams.topMargin,
                            occupancyWidth + childWidth - childParams.rightMargin,
                            occupancyHeight + childHeight - childParams.bottomMargin
                        )
                        occupancyHeight += eachLineMaxHeight + itemSpaceHeight
                        eachLineMaxHeight = 0
                        occupancyWidth = 0
                        continue
                    }

                    childView.layout(
                        occupancyWidth + childParams.leftMargin,
                        occupancyHeight + childParams.topMargin,
                        occupancyWidth + childWidth - childParams.rightMargin,
                        occupancyHeight + childHeight - childParams.bottomMargin
                    )
                    occupancyWidth += (childWidth + childParams.rightMargin + itemSpaceWidth + childParams.leftMargin)
                }
            }
            Mode.VARIATION -> {
                for (i in 0 until childCount) {
                    var childView = getChildAt(i)
                    var childWidth = childView.measuredWidth
                    Log.d("child width ", childWidth.toString())
                    var childHeight = childView.measuredHeight
                    var childParams = childView.layoutParams as ViewGroup.MarginLayoutParams
                    eachLineMaxHeight = Math.max(childHeight, eachLineMaxHeight)
                    if (occupancyWidth + childWidth > r) {
                        occupancyHeight += eachLineMaxHeight + itemSpaceHeight
                        eachLineMaxHeight = 0
                        occupancyWidth = l
                        childView.layout(
                            occupancyWidth + childParams.leftMargin,
                            occupancyHeight + childParams.topMargin,
                            occupancyWidth + childWidth - childParams.rightMargin,
                            occupancyHeight + childHeight - childParams.bottomMargin
                        )
                        eachLineMaxHeight = Math.max(childHeight, eachLineMaxHeight)
                        occupancyWidth += childWidth
                        continue
                    }

                    childView.layout(
                        occupancyWidth + childParams.leftMargin,
                        occupancyHeight + childParams.topMargin,
                        occupancyWidth + childWidth - childParams.rightMargin,
                        occupancyHeight + childHeight - childParams.bottomMargin
                    )
                    occupancyWidth += (childWidth + childParams.rightMargin + itemSpaceWidth + childParams.leftMargin)
                }
            }
        }

    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }
}