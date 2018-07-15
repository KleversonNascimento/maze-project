package labirintop;

public class Fila {

    private No head;
    private No tail;

    public Fila() {
        head = null;
        tail = null;
    }

    public void enfileirar(Object obj) {
        No no = new No(obj);
        if (tail == null) {
            head = no;
            tail = no;
        } else {
            tail.setProx(no);
            tail = no;
        }
    }

    public Object desenfileirar() {
        Object obj;
        if (head != null) {
            obj = head.getObj();
            head = head.getProx();
            if (head == null) {
                tail = null;
            }
        } else {
            obj = null;
        }
        return obj;
    }

    public boolean isVazio() {
        return head == null;
    }

    public boolean isUnico() {
        return head == tail;
    }
}
