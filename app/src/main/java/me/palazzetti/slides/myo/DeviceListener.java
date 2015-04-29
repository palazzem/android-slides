package me.palazzetti.slides.myo;

import com.thalmic.myo.AbstractDeviceListener;
import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;

import me.palazzetti.slides.Constants.Actions;

public class DeviceListener extends AbstractDeviceListener {
    private GestureRecognition mCallback;

    public DeviceListener(GestureRecognition callback) {
        mCallback = callback;
    }

    @Override
    public void onPose(Myo myo, long timestamp, Pose pose) {
        super.onPose(myo, timestamp, pose);

        // doesn't lock the Myo until an explicit action is done
        // such as myo.lock()
        myo.unlock(Myo.UnlockType.HOLD);

        // unused cases are in the following list just for tutorial purposes
        switch (pose) {
            case DOUBLE_TAP:
                // double tap works as a locker/unlocker
                myo.lock();
                break;
            case WAVE_IN:
                mCallback.onPoseRecognition(Actions.PREVIOUS);
                break;
            case WAVE_OUT:
                mCallback.onPoseRecognition(Actions.NEXT);
                break;
            case REST:
                // noop
                break;
            case FIST:
                // noop
                break;
            case FINGERS_SPREAD:
                // noop
                break;
            case UNKNOWN:
                // noop
                break;
        }
    }
}
