package Models;

public enum Discipline {
    BUTTERFLY,
    CRAWL,
    BACK,
    BREAST;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
