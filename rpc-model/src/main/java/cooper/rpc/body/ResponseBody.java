package cooper.rpc.body;

/**
 * Created by emirbobo on 2016/9/30.
 */
public class ResponseBody implements java.io.Serializable {

    private int uid;

    private int resultCode;

    private String jsonResult;

    public ResponseBody() {
    }

    public ResponseBody(int resultCode, String jsonResult) {
        this.resultCode = resultCode;
        this.jsonResult = jsonResult;
    }

    public ResponseBody(int uid, int resultCode, String jsonResult) {
        this.uid = uid;
        this.resultCode = resultCode;
        this.jsonResult = jsonResult;
    }

    public int getResultCode() {

        return resultCode;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(String jsonResult) {
        this.jsonResult = jsonResult;
    }

    @Override
    public String toString() {
        return "ResponseBody{" +
                "uid=" + uid +
                ", resultCode=" + resultCode +
                ", jsonResult='" + jsonResult + '\'' +
                '}';
    }
}
