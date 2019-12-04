package main;
// This class is for all our configuration data. By putting it all in one place, we can easily make changes to the program without having to hunt down where in the code a constant is defined.
// A Configuration class is provided to allow you a central location to configure your application name, data path,
// prime file name and cross file name. These defaults and values must be in Config and not hard-coded into the
// body of the program.

public class Config
{
	public final static String DATAPATH = "data/"; // If you don't know what the static keyword does, you better go look
													// it up now.
	// Prime file: One prime per line.
	// Cross file: Two primes per line, separated by a comma.
}