/*
 * Copyright 2007-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.numerals

/**
 * Class for using cardinals from command line.<br/>
 *
 * <p>Usage:
 * <pre>
 *   numerals 123 45 1000
 *   numerals --locale es 123
 *   numerals 123 --locale es
 *   numerals 123 --locale=es
 *   numerals --help
 * </pre>
 *
 * <p>The {@code --locale} flag may appear anywhere in the argument list, in
 * either {@code --locale es} or {@code --locale=es} form; the value accepts
 * both {@code es} and {@code es_MX} / {@code es-MX}. Without {@code --locale}
 * it uses the JVM default locale.
 *
 * @author Josue Mart&iacute;nez Buenrrostro (@josuemb)
 */
class Console {

	private static final String LOCALE_FLAG = "--locale"

	static void main(args) {
		Locale locale = Locale.getDefault()
		List<String> numbers = []

		for (int i = 0; i < args.length; i++) {
			String arg = args[i]
			if (arg == "--help" || arg == "-h") {
				printHelp()
				return
			} else if (arg == LOCALE_FLAG) {
				// "--locale es" form: the value is the next argument.
				if (i + 1 < args.length) {
					locale = parseLocale(args[++i])
				}
			} else if (arg.startsWith(LOCALE_FLAG + "=")) {
				// "--locale=es" form.
				locale = parseLocale(arg.substring(LOCALE_FLAG.length() + 1))
			} else {
				numbers.add(arg)
			}
		}

		// No numbers to convert: show help instead of printing nothing.
		if (numbers.isEmpty()) {
			printHelp()
			return
		}

		numbers.each { number ->
			try {
				String cardinal = CardinalUtil.getCardinal(number, locale)
				println "[$number]=[$cardinal]"
			} catch (Exception e) {
				println "[$number]=[Error:${e.message}]"
			}
		}
	}

	private static Locale parseLocale(String value) {
		// Accept both es_MX (Java style) and es-MX (BCP 47) for the value.
		return Locale.forLanguageTag(value.replace('_' as char, '-' as char))
	}

	private static void printHelp() {
		println([
			"numerals - generate the cardinal name of a number (numbers to words).",
			"",
			"Usage:",
			"  numerals <number> [<number> ...] [--locale <lang[_COUNTRY]>]",
			"  numerals --help | -h",
			"",
			"Options:",
			"  --locale <lang[_COUNTRY]>   Language for the output. Accepts es, es_MX,",
			"                              es-MX. May appear anywhere in the arguments;",
			"                              --locale=<value> is also accepted. Defaults to",
			"                              the JVM locale when omitted.",
			"  -h, --help                  Show this help and exit.",
			"",
			"Supported locales:",
			"  es      Spanish            en      English",
			"  en_GB   British English    pt      Portuguese",
			"  it      Italian            ca      Catalan",
			"  gl      Galician           ro      Romanian",
			"  oc      Occitan            ast     Asturian",
			"  an      Aragonese",
			"",
			"Numbers are non-negative integers of up to 24 digits.",
			"",
			"Examples:",
			"  numerals 123",
			"      -> [123]=[ciento veintitrés]   (with the JVM locale set to Spanish)",
			"  numerals 45789 --locale es",
			"      -> [45789]=[cuarenta y cinco mil setecientos ochenta y nueve]",
			"  numerals 101 --locale en_GB",
			"      -> [101]=[one hundred and one]",
			"  numerals 16 22 100 --locale=es",
			"      -> [16]=[dieciséis]  [22]=[veintidós]  [100]=[cien]"
		].join(System.lineSeparator()))
	}
}