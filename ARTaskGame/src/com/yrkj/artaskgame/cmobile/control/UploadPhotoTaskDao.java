package com.yrkj.artaskgame.cmobile.control;

import org.json.JSONObject;

import android.text.TextUtils;

import com.yrkj.artaskgame.cmobile.control.UploadPhotoTaskDao.TaskDaoResult;
import com.yrkj.util.basedao.BaseBean;
import com.yrkj.util.basedao.BaseTaskDao;
import com.yrkj.util.basedao.BeanSon;
import com.yrkj.util.http.HttpRequestValue;
import com.yrkj.util.http.InputFileObj;

public class UploadPhotoTaskDao extends BaseTaskDao<Integer, Object, TaskDaoResult> {

    public static final String BIZID = "UploadPhotoTaskDao";
    public PostTaskListener postl = null;
    public PreTaskListener prel = null;
    public RunningTaskListener running = null;
    protected UploadPhotoTaskDao mDao = null;
   
    public UploadPhotoTaskDao(String urlPath){
         mURLPath = urlPath;
    }
    
    public UploadPhotoTaskDao addGetParams( 
       ){
        return this;
    }

    public UploadPhotoTaskDao addPostParams(
    		String method,
    		String taskname,
    		String taskid,
    		String username,
    		String userid
    		){
    	mReqPostValues = new HttpRequestValue();
        mReqPostValues.Add("method", method);
        mReqPostValues.Add("taskname", taskname);
        mReqPostValues.Add("taskid", taskid);
        mReqPostValues.Add("username", username);
        mReqPostValues.Add("userid", userid);
    	return this;
    }
    

    public UploadPhotoTaskDao addFileParams(
            InputFileObj imgvalue
    ){
        mReqFileValues = new HttpRequestValue();
        mReqFileValues.AddFile("imgvalue", imgvalue);
        return this;
    }

    private void run(int id){
         mDao = new UploadPhotoTaskDao(mURLPath);
         //Init
         mDao.setTask(prel, postl);
         mDao.setOnRunningListener(running);
         mDao.setFileParams(mReqFileValues);
         mDao.setPostParams(mReqPostValues);
         mDao.setGetParams(mReqValues);
         mDao.setId(id);
         mDao.execute();
         mReqFileValues = null;
         mReqPostValues = null;
         mReqValues = null;
    }

    public void runTask(int id){
        
        if(mDao == null){
            run(id);
            return;
        }
        
        if(mDao.getStatus() == Status.RUNNING){
            if(running != null){
                running.UpdateMyInfoDetailTaskDao_onRunning();
            }
            return;
        }
        
        if(mDao.getStatus() == Status.FINISHED){
            run(id);
            return;
        }

        
    }

    public void runTask(){
        runTask(0);
    }
    
    public void setTask(PreTaskListener pl,PostTaskListener pol){
        prel = pl;
        postl = pol;
    }
    
    public void setOnRunningListener(RunningTaskListener l){
        running = l;
    }
    
    public interface PostTaskListener{
         void UpdateMyInfoDetailTaskDao_onPostTask(int taskId,boolean isSuccess,String errMsg,ResponseHeaderBean result);
    }
    public interface PreTaskListener{
        HttpRequestValue UpdateMyInfoDetailTaskDao_onPreTask(int taskId);
    }
   
    public interface RunningTaskListener{
        void UpdateMyInfoDetailTaskDao_onRunning();
    }
    
    @Override
    protected TaskDaoResult doBack() {
        try {
            
            TaskDaoResult taskResult = new TaskDaoResult();
            String resultStr = doHttp();
            
            if(TextUtils.isEmpty(resultStr) || resultStr.toUpperCase().equals("NULL")){
                taskResult.IsSuccess = false;
                taskResult.ErrMsg = COMM_ERRMSG;
                return taskResult;
            }

            ResponseHeaderBean ResponseHeaderBeanObj 
//            	= null;
            	= doBeanSon(new ResponseHeaderBeanSON(),
                    new JSONObject(resultStr));
            
            String reportText = ResponseHeaderBeanObj.checkBeanIsSuccess();
            if(reportText != null){
                taskResult.IsSuccess = false;
                taskResult.ErrMsg = reportText;
                return taskResult;
            }
            
            taskResult.IsSuccess = true;
            taskResult.ErrMsg = resultStr;
            taskResult.Bean = ResponseHeaderBeanObj;
            
            return taskResult;
            
            
        } catch (Exception e) {
            e.printStackTrace();
            TaskDaoResult taskResult = new TaskDaoResult();
            taskResult.IsSuccess = false;
            taskResult.ErrMsg = COMM_ERRMSG;
            return taskResult;
        }
    }
    
    public static HttpRequestValue setParams(
            ){
        HttpRequestValue v = new HttpRequestValue();
        return v;
    }
    
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(prel != null){
            //mReqValues = prel.UpdateMyInfoDetailTaskDao_onPreTask(mTaskId);
            HttpRequestValue defaultReqValues = prel.UpdateMyInfoDetailTaskDao_onPreTask(mTaskId);
            if(mReqValues == null){
                mReqValues = defaultReqValues;
            }
        }
    }
    
    @Override
    protected void onPostExecute(TaskDaoResult result) {
        super.onPostExecute(result);
        if(postl != null){
            postl.UpdateMyInfoDetailTaskDao_onPostTask(mTaskId,result.IsSuccess,result.ErrMsg,result.Bean);
        }
    }
    
    public class TaskDaoResult{
        public Boolean IsSuccess = false;
        public String ErrMsg = "";
        public ResponseHeaderBean Bean = null;
    }
    
    public class ResponseHeaderBean extends BaseBean  {
     
    	public static final String successColumnName = "success";
    	public static final String errormessageColumnName = "errormessage";
    	public static final String bodyColumnName = "body";
    	
        
        public String success = "";
        public String errormessage = "";
        public String body = "";
        public SubmitResultBean bodyObj = null;
        
        public String ORGJosnData = "";
        
        public void createChildBean() throws Exception{
            if(checkJson(body)){
                bodyObj = doBeanSon(new SubmitResultBeanSON(),
                        new JSONObject(body));
            }
        }
        public String checkBeanIsSuccess(){
            if(success.equals("-1")){
                return errormessage;
            }
            return null;
        }
        
        @Override
        public String getData(String path) {
//            if(false){}
//            else if(getBeanFiledPath(path,tokenColumnName)){
//                return token;
//            }
//            else if(getBeanFiledPath(path,business_idColumnName)){
//                return business_id;
//            }
//            else if(getBeanFiledPath(path,report_typeColumnName)){
//                return report_type;
//            }
//            else if(getBeanFiledPath(path,report_textColumnName)){
//                return report_text;
//            }
//            else if(getBeanFiledPath(path,gzip_typeColumnName)){
//                return gzip_type;
//            }
//            else if(getBeanFiledPath(path,decode_typeColumnName)){
//                return decode_type;
//            }
//            else if(getBeanFiledPath(path,useridColumnName)){
//                return userid;
//            }
//            else if(getBeanFiledPath(path,bodyColumnName+"/")){
//                return bodyObj.getData(getBeanChildPath(path));
//            }
            
            return "";
        }
    }
    
    private class ResponseHeaderBeanSON extends BeanSon<ResponseHeaderBean>{

        public ResponseHeaderBean convertJsonToBean(JSONObject jsonObj ) throws Exception{
            ResponseHeaderBean tempData = new ResponseHeaderBean();
            tempData.success = jsonObj.getString(tempData.successColumnName);
            tempData.errormessage = jsonObj.getString(tempData.errormessageColumnName);
            tempData.body = jsonObj.getString(tempData.bodyColumnName);
            tempData.ORGJosnData = jsonObj.toString();
            tempData.createChildBean();
            return tempData;
        }
    }
    
    
    public class SubmitResultBean extends BaseBean  {
     
//        public static final String report_typeColumnName = "report_type";
//        public static final String report_textColumnName = "report_text";
        
        public static final String statusColumnName = "status";
        public static final String statusmessageColumnName = "statusmessage";
        
        public String ORGJosnData = "";
        
        public String status = "";
        public String statusmessage = "";
        public void createChildBean() throws Exception{
        }
        
//        public String checkBeanIsSuccess(){
//            if(report_type.equals("-1")){
//                return report_text;
//            }
//            return null;
//        }
        
        @Override
        public String getData(String path) {
//            if(false){}
//            else if(getBeanFiledPath(path,report_typeColumnName)){
//                return report_type;
//            }
//            else if(getBeanFiledPath(path,report_textColumnName)){
//                return report_text;
//            }
            
            return "";
        }
    }
    
    private class SubmitResultBeanSON extends BeanSon<SubmitResultBean>{

        public SubmitResultBean convertJsonToBean(JSONObject jsonObj ) throws Exception{
            SubmitResultBean tempData = new SubmitResultBean();

            tempData.status = jsonObj.getString(tempData.statusColumnName);
            tempData.statusmessage = jsonObj.getString(tempData.statusmessageColumnName);
            tempData.ORGJosnData = jsonObj.toString();
            tempData.createChildBean();
            return tempData;
        }
        
    }
    
}
