void main(String[] args) {
    if (args.length == 0) {
        return;
    }
    int stranica = (int) Math.ceil(Math.sqrt(args[0].length()));
    int st_znakov = (stranica * stranica);
    for (int i = 0; i < st_znakov; i++) {

        if (i < args[0].length()) {
            IO.print(" " + args[0].charAt(i) + " "); // dodani presledki so samo za "olepševanje" izgleda kvadrata
        } else {
            IO.print(" . ");
        }
        if ((i + 1) % stranica == 0) {
            IO.print('\n');
        }
    }
}
