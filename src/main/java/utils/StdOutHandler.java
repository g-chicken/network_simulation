package utils;

import java.util.logging.Level;
import java.util.logging.StreamHandler;

/**
 * StdOutHandler is handler which output log to std.
 */
public class StdOutHandler extends StreamHandler {
  {
    // output std
    setOutputStream(System.out);

    // if it do not set Level.ALL, it can not control log level.
    setLevel(Level.ALL);
  }
}
