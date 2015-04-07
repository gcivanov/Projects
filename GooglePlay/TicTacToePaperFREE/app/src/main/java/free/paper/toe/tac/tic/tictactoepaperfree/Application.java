package free.paper.toe.tac.tic.tictactoepaperfree;

import free.paper.toe.tac.tic.typefaces.FontsOverride;


public class Application extends android.app.Application{
    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/tempussansitc.ttf");



    }
}
