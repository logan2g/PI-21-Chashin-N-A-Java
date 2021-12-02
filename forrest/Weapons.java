package forrest;

public enum Weapons {
    n2 (2),
    n4 (4),
    n6 (6);

    private int n;

    Weapons(int n){
        this.n = n;
    }

    @Override
    public String toString(){
        return String.valueOf(n);
    }
}
