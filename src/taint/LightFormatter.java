package taint;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LightFormatter extends Formatter {

	@Override
	public String format(LogRecord r) {
		return r.getMessage();
	}

}
