//package com.yrkj.util.ui.layout;
//
//
//import org.json.JSONObject;
//
//import com.lc.sm.R;
//import com.yrkj.util.JSONUtil;
//import com.yrkj.util.ui.json.Bindable;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.text.Html;
//import android.text.Layout;
//import android.text.Selection;
//import android.text.Spannable;
//import android.text.SpannableStringBuilder;
//import android.text.method.LinkMovementMethod;
//import android.text.method.Touch;
//import android.text.style.ClickableSpan;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//import android.widget.TextView;
//
//public class TextViewFixTouchConsume extends TextView implements Bindable{
//boolean dontConsumeNonUrlClicks = true;
//boolean linkHit;
//
//private String key ;
//
//@Override
//public void bind(JSONObject jsonObj, Activity acty) {
//	// TODO Auto-generated method stub
//	this.setText(JSONUtil.getJsonStringRecursive(jsonObj, key));
//}
//
//
//public TextViewFixTouchConsume(Context context) {
//    super(context);
//}
//
//public TextViewFixTouchConsume(Context context, AttributeSet attrs) {
//	
//    super(context, attrs);
//    
//    TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.bind);
//	key = typedArray.getString(R.styleable.bind_key);
//	typedArray.recycle();
//	
//}
//
//public TextViewFixTouchConsume(Context context, AttributeSet attrs, int defStyle) {
//    super(context, attrs, defStyle);
//    
//    TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.bind);
//   	key = typedArray.getString(R.styleable.bind_key);
//   	typedArray.recycle();
//   	
//}
//
//@Override
//public boolean onTouchEvent(MotionEvent event) {
//    linkHit = false;
//    boolean res = super.onTouchEvent(event);
//
//    if (dontConsumeNonUrlClicks)
//        return linkHit;
//    return res;
//
//}
//
//public void setTextViewHTML(String html)
//{
//    CharSequence sequence = Html.fromHtml(html);
//    SpannableStringBuilder strBuilder = new SpannableStringBuilder(sequence);
//    setText(strBuilder);
//}
//
//public static class LocalLinkMovementMethod extends LinkMovementMethod{
//    static LocalLinkMovementMethod sInstance;
//
//
//    public static LocalLinkMovementMethod getInstance() {
//        if (sInstance == null)
//            sInstance = new LocalLinkMovementMethod();
//
//        return sInstance;
//    }
//
//    @Override
//    public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {
//        int action = event.getAction();
//
//        if (action == MotionEvent.ACTION_UP ||
//                action == MotionEvent.ACTION_DOWN) {
//            int x = (int) event.getX();
//            int y = (int) event.getY();
//
//            x -= widget.getTotalPaddingLeft();
//            y -= widget.getTotalPaddingTop();
//
//            x += widget.getScrollX();
//            y += widget.getScrollY();
//
//            Layout layout = widget.getLayout();
//            int line = layout.getLineForVertical(y);
//            int off = layout.getOffsetForHorizontal(line, x);
//
//            ClickableSpan[] link = buffer.getSpans(off, off, ClickableSpan.class);
//
//            if (link.length != 0) {
//                if (action == MotionEvent.ACTION_UP) {
//                    link[0].onClick(widget);
//                } else if (action == MotionEvent.ACTION_DOWN) {
//                    Selection.setSelection(buffer,
//                            buffer.getSpanStart(link[0]),
//                            buffer.getSpanEnd(link[0]));
//                }
//
//                if (widget instanceof TextViewFixTouchConsume){
//                    ((TextViewFixTouchConsume) widget).linkHit = true;
//                }
//                return true;
//            } else {
//                Selection.removeSelection(buffer);
//                Touch.onTouchEvent(widget, buffer, event);
//                return false;
//            }
//        }
//        return Touch.onTouchEvent(widget, buffer, event);
//    }
//}
//}