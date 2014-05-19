package com.yrkj.elderlycareassess.layout;

import java.util.Map;
import android.app.Activity;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.ui.layout.BaseLayout;
import com.yrkj.util.ui.layout.LayoutDataAdapter;
import com.yrkj.elderlycareassess.R;


public class Recorder extends BaseLayout{

    public static final int RecorderViewId = R.id.recorderView;
    public static final int TextView1Id = R.id.textView1;
    public static final int RecordTextId = R.id.recordText;
    public static final int LinearLayout1Id = R.id.linearLayout1;
    public static final int ChronoTimeId = R.id.chronoTime;
    public static final int ProgressBar1Id = R.id.progressBar1;
    public static final int Player2Id = R.id.player2;
    public static final int Recorder2Id = R.id.recorder2;

    protected android.widget.TableLayout mRecorderView;
    protected android.widget.TextView mTextView1;
    protected android.widget.EditText mRecordText;
    protected android.widget.RelativeLayout mLinearLayout1;
    protected android.widget.Chronometer mChronoTime;
    protected android.widget.ProgressBar mProgressBar1;
    protected android.widget.ImageView mPlayer2;
    protected android.widget.ImageView mRecorder2;

    protected Activity mCurActy;

    public Recorder(Activity acty){
        mCurActy = acty;
        mRecorderView = (android.widget.TableLayout) acty.findViewById(RecorderViewId);
        mTextView1 = (android.widget.TextView) acty.findViewById(TextView1Id);
        mRecordText = (android.widget.EditText) acty.findViewById(RecordTextId);
        mLinearLayout1 = (android.widget.RelativeLayout) acty.findViewById(LinearLayout1Id);
        mChronoTime = (android.widget.Chronometer) acty.findViewById(ChronoTimeId);
        mProgressBar1 = (android.widget.ProgressBar) acty.findViewById(ProgressBar1Id);
        mPlayer2 = (android.widget.ImageView) acty.findViewById(Player2Id);
        mRecorder2 = (android.widget.ImageView) acty.findViewById(Recorder2Id);
    }   

    public Recorder(android.view.View acty){
        mRecorderView = (android.widget.TableLayout) acty.findViewById(RecorderViewId);
        mTextView1 = (android.widget.TextView) acty.findViewById(TextView1Id);
        mRecordText = (android.widget.EditText) acty.findViewById(RecordTextId);
        mLinearLayout1 = (android.widget.RelativeLayout) acty.findViewById(LinearLayout1Id);
        mChronoTime = (android.widget.Chronometer) acty.findViewById(ChronoTimeId);
        mProgressBar1 = (android.widget.ProgressBar) acty.findViewById(ProgressBar1Id);
        mPlayer2 = (android.widget.ImageView) acty.findViewById(Player2Id);
        mRecorder2 = (android.widget.ImageView) acty.findViewById(Recorder2Id);
    }   
    public android.widget.TableLayout getRecorderView() {
        return mRecorderView;
    }
    public android.widget.TextView getTextView1() {
        return mTextView1;
    }
    public android.widget.EditText getRecordText() {
        return mRecordText;
    }
    public android.widget.RelativeLayout getLinearLayout1() {
        return mLinearLayout1;
    }
    public android.widget.Chronometer getChronoTime() {
        return mChronoTime;
    }
    public android.widget.ProgressBar getProgressBar1() {
        return mProgressBar1;
    }
    public android.widget.ImageView getPlayer2() {
        return mPlayer2;
    }
    public android.widget.ImageView getRecorder2() {
        return mRecorder2;
    }

    public void bindData(LayoutDataAdapter adp,BaseBean data){
        if(adp == null || data == null){
            return;
        }
        
        if(adp.BindJoinFiledList != null){
            java.util.Iterator iter = adp.BindJoinFiledList.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Object key = entry.getKey();
                Object val = entry.getValue();
                int viewKey = (Integer) key;
                LayoutDataAdapter.JoinData joinData = (LayoutDataAdapter.JoinData) val;
                
                switch (viewKey) {
                case RecorderViewId:
                    setViewData(adp,getRecorderView(),data,joinData.formatString,joinData.data);
                    break;
                case TextView1Id:
                    setViewData(adp,getTextView1(),data,joinData.formatString,joinData.data);
                    break;
                case RecordTextId:
                    setViewData(adp,getRecordText(),data,joinData.formatString,joinData.data);
                    break;
                case LinearLayout1Id:
                    setViewData(adp,getLinearLayout1(),data,joinData.formatString,joinData.data);
                    break;
                case ChronoTimeId:
                    setViewData(adp,getChronoTime(),data,joinData.formatString,joinData.data);
                    break;
                case ProgressBar1Id:
                    setViewData(adp,getProgressBar1(),data,joinData.formatString,joinData.data);
                    break;
                case Player2Id:
                    setViewData(adp,getPlayer2(),data,joinData.formatString,joinData.data);
                    break;
                case Recorder2Id:
                    setViewData(adp,getRecorder2(),data,joinData.formatString,joinData.data);
                    break;
                }
            }
        }
        
        if(adp.BindFiledList != null){
            java.util.Iterator iter = adp.BindFiledList.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Object key = entry.getKey();
                Object val = entry.getValue();
                int viewKey = (Integer) key;
                String path = (String) val;
                switch (viewKey) {
                case RecorderViewId:
                    setViewData(adp,getRecorderView(),data,"",path);
                    break;
                case TextView1Id:
                    setViewData(adp,getTextView1(),data,"",path);
                    break;
                case RecordTextId:
                    setViewData(adp,getRecordText(),data,"",path);
                    break;
                case LinearLayout1Id:
                    setViewData(adp,getLinearLayout1(),data,"",path);
                    break;
                case ChronoTimeId:
                    setViewData(adp,getChronoTime(),data,"",path);
                    break;
                case ProgressBar1Id:
                    setViewData(adp,getProgressBar1(),data,"",path);
                    break;
                case Player2Id:
                    setViewData(adp,getPlayer2(),data,"",path);
                    break;
                case Recorder2Id:
                    setViewData(adp,getRecorder2(),data,"",path);
                    break;
                    
                default:
                    break;
                }
            }
         }
    }
}
