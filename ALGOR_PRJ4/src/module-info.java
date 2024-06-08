module ALGOR_PRJ4 {
	requires javafx.controls;
	requires jdk.xml.dom;
	requires java.desktop;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml,javafx.base,javafx.controls,javafx.web;
}
