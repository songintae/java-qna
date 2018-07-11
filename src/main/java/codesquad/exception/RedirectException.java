package codesquad.exception;

public class RedirectException extends RuntimeException {

    private String url;
    public RedirectException(String url){
        this.url = url;
    }

    public String getRedirectUrl(){
        return this.url;
    }
}
