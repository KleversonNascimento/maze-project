package labirintop;

public class No {

    private Object obj;
    private No prox;

    public No(Object obj) {
        this.obj = obj;
        prox = null;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }
}
