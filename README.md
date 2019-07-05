# dynamic-logging

This tool can dynamic set logger level for logging tool log4j / log4j2 / logback .

ENV : java 1.8+

For web servlet 3.1+ you can 'curl http://xxxx/change-log-level?logger=xx&level=xxx' to dynamic setting logger level.

URL you can set by `System.getProperty(PATH_KEY)` or `System.getenv(PATH_KEY)`.

