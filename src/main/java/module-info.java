module org.boardgame.boardgamehelper {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens org.boardgame.boardgamehelper to javafx.fxml;
    exports org.boardgame.boardgamehelper;
    exports org.boardgame.boardgamehelper.controllers;
    opens org.boardgame.boardgamehelper.controllers to javafx.fxml;
}