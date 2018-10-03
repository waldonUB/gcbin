public interface DefaultImpl {
    int foo ();
    default int plus(int a, int b) {
        return a + b;
    }
}
