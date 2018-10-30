package wind.acoder.utils;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.support.annotation.AnimatorRes;
import android.view.View;
import android.view.ViewGroup;

public class ValueAnimUtil {
    //无监听事件，不设置时长
    public static void animate(@AnimatorRes int id, View... views){
        for (int i = 0; i < views.length; i++){
            inflate(id, views[i], null).start();
        }
    }

    //无监听事件，需设置时长
    public static void animate(@AnimatorRes int id, long duration, View... views){
        for (int i = 0; i < views.length; i++){
            inflate(id, views[i], null).setDuration(duration).start();
        }
    }

    //有监听事件
    public static void animate(@AnimatorRes int id, View view, Animator.AnimatorListener l){
        inflate(id, view, l).start();
    }

    private static Animator inflate(@AnimatorRes int id, View view, Animator.AnimatorListener l){
        Animator animator = AnimatorInflater.loadAnimator(view.getContext(), id);
        animator.setTarget(view);
        animator.addListener(l == null ? new AnimatorListenerAdapter() {} : l);
        return animator;
    }

    public static void startCurtainAnimate(View view, long duration, int start, int end){
        curtainAnimate(view, start, end).setDuration(duration).start();
    }

    private static ValueAnimator curtainAnimate(final View v, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        final int originWidth = v.getMeasuredWidth();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator arg) {
                int value = (int) arg.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.height = value;
                layoutParams.width = originWidth;
                v.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }
}
