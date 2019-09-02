package sample.shaker;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class shake {
    private TranslateTransition translateTransition;

    public shake(Node node) {
        translateTransition =
                new TranslateTransition(Duration.millis(50), node);
        translateTransition.setFromX(0f);
        translateTransition.setByX(10f);
        translateTransition.setCycleCount(2);
        translateTransition.setAutoReverse(true);

    }

    public void shake() {
        translateTransition.playFromStart();
    }
}
