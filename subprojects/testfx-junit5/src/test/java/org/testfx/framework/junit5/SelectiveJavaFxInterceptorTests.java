/*
 * Copyright 2013-2014 SmartBear Software
 * Copyright 2014-2021 The TestFX Contributors
 *
 * Licensed under the EUPL, Version 1.1 or - as soon they will be approved by the
 * European Commission - subsequent versions of the EUPL (the "Licence"); You may
 * not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 * http://ec.europa.eu/idabc/eupl.html
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the Licence is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the Licence for the
 * specific language governing permissions and limitations under the Licence.
 */
package org.testfx.framework.junit5;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.JavaFXInterceptorUtils.SelectiveJavaFxInterceptor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@link org.testfx.framework.junit5.JavaFXInterceptorUtils.SelectiveJavaFxInterceptor}.
 */
@ExtendWith(ApplicationExtension.class)
@ExtendWith(SelectiveJavaFxInterceptor.class)
public class SelectiveJavaFxInterceptorTests {
    @Start
    public void start(final Stage stage) {
        // usual FX initialisation
        // ...
        assertTrue(Platform.isFxApplicationThread());
        stage.setScene(new Scene(new StackPane(), 100, 100));
        stage.show();
    }

    @TestFx // forces execution in JavaFX thread
    public void testJavaFxThreadSafety() {
        // verifies that this test is indeed executed in the JavaFX thread
        assertTrue(Platform.isFxApplicationThread());

        // perform the regular JavaFX thread safe assertion tests
        // ...
    }

    /**
     * explicitly not executed in JavaFX thread; 
     * for different behaviour use: {@code @ExtendWith(JavaFxInterceptor.class)}
     */
    @Test
    public void testNonJavaFx() {
        // verifies that this test is not executed within the JavaFX thread
        assertFalse(Platform.isFxApplicationThread());

        // perform the regular non-JavaFX thread-related assertion tests
        // ...
    }
}
