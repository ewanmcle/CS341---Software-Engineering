module CS341_HW5 {
	requires java.desktop;
	requires org.junit.jupiter.api;

	opens statsapp to org.junit.platform.commons;
}
