import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ColorTest {

    @org.junit.jupiter.api.Test
    void getRed() {
        Color colorTest = new Color(31, 240, 255);
        assertEquals(31, colorTest.getRed());
    }

    @org.junit.jupiter.api.Test
    void setRed() {
        Color colorTest = new Color(31, 240, 255);
        colorTest.setRed(31);
        int expectedVal = 31;
        assertEquals(expectedVal, colorTest.getRed());
    }

    @org.junit.jupiter.api.Test
    void getGreen() {
        Color colorTest = new Color(31, 240, 255);
        assertEquals(240, colorTest.getGreen());
    }

    @org.junit.jupiter.api.Test
    void setGreen() {
        Color colorTest = new Color(31, 240, 255);
        colorTest.setGreen(50);
        int expectedVal = 50;
        assertEquals(expectedVal, colorTest.getGreen());
    }

    @org.junit.jupiter.api.Test
    void getBlue() {
        Color colorTest = new Color(31, 240, 255);
        assertEquals(255, colorTest.getBlue());
    }

    @org.junit.jupiter.api.Test
    void setBlue() {
        Color colorTest = new Color(31, 240, 255);
        colorTest.setBlue(50);
        int expectedVal = 50;
        assertEquals(expectedVal, colorTest.getBlue());
    }

    @org.junit.jupiter.api.Test
    void RGBtoHSB() {
        Color colorTest = new Color(31, 240, 255);
        ArrayList<Float> expectedArr = new ArrayList<>();
        expectedArr.add(0.5111607f);
        expectedArr.add(0.8784314f);
        expectedArr.add(1.0f);

        ArrayList<Float> actualArr = new ArrayList<>();
        Color.RGBtoHSB(colorTest.getRed(), colorTest.getGreen(), colorTest.getBlue(), actualArr); // Use RGBtoHSB method
        Assertions.assertEquals(expectedArr.get(0), actualArr.get(0));
        Assertions.assertEquals(expectedArr.get(1), actualArr.get(1));
        Assertions.assertEquals(expectedArr.get(2), actualArr.get(2));
    }

    @org.junit.jupiter.api.Test
    void RGBtoHSL() {
        Color colorTest = new Color(31, 240, 255);
        ArrayList<Float> expectedArr = new ArrayList<>();
        expectedArr.add(184.01785f);
        expectedArr.add(100.000015f);
        expectedArr.add(56.078434f);

        ArrayList<Float> actualArr = new ArrayList<>();
        Color.RGBtoHSL(colorTest.getValue(), actualArr);
        assertEquals(expectedArr.get(0), actualArr.get(0));
        assertEquals(expectedArr.get(1), actualArr.get(1));
        assertEquals(expectedArr.get(2), actualArr.get(2));
    }

    @org.junit.jupiter.api.Test
    void RGBtoCMYK() {
        Color colorTest = new Color(31, 240, 255);
        ArrayList<Integer> expectedArr = new ArrayList<>();
        expectedArr.add(87);
        expectedArr.add(5);
        expectedArr.add(0);
        expectedArr.add(0);
        ArrayList<Integer> actualArr = new ArrayList<>();
        Color.RGBtoCMYK(colorTest.getRed(), colorTest.getGreen(), colorTest.getBlue(), actualArr);
        assertEquals(expectedArr.get(0), actualArr.get(0));
        assertEquals(expectedArr.get(1), actualArr.get(1));
        assertEquals(expectedArr.get(2), actualArr.get(2));
        assertEquals(expectedArr.get(3), actualArr.get(3));
    }

    @org.junit.jupiter.api.Test
    void decode() {
        Color colorTest = new Color(31, 240, 255);
        Color actualVal = Color.decode("0x1FF0FF");
        assertEquals(colorTest.getRed(), actualVal.getRed());
        assertEquals(colorTest.getGreen(), actualVal.getGreen());
        assertEquals(colorTest.getBlue(), actualVal.getBlue());
    }
}