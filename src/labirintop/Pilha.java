package labirintop;

public class Pilha {

    private No topo;

    public Pilha() {
        topo = null;
    }

    public void empilhar(Object obj) {
        No no = new No(obj);
        no.setProx(topo);
        topo = no;
    }

    public Object desempilhar() {
        Object obj;
        if (topo != null) {
            obj = topo.getObj();
            topo = topo.getProx();
        } else {
            obj = null;
        }
        return obj;
    }

    public boolean isVazio() {
        return topo == null;
    }
}
