package kmp.kutils



public abstract class KLoggerAbs {

    enum class KLoggerType {
        VERBOSE, DEBUG, INFO, WARN, ERROR
    }

    private var kLogger: ((type: KLoggerType, messages: Array<out Any?>) -> Unit)? = null
    private var kLoggerThrowable: ((type: KLoggerType, message: String, throwable: Throwable) -> Unit)? = null

    fun setLogger(logger: (type: KLoggerType, messages: Array<out Any?>) -> Unit) {
        this.kLogger = logger
    }

    fun setLoggerThrowable(logger: (type: KLoggerType, message: String, throwable: Throwable) -> Unit) {
        this.kLoggerThrowable = logger
    }

    open fun logger(type: KLoggerType, vararg messages: Any?) {
        kLogger?.invoke(type, messages)
    }

    open fun logThrowable(type: KLoggerType, message: String, throwable: Throwable) {
        kLoggerThrowable?.invoke(type, message, throwable)
    }


    fun d(vararg messages: Any) {
        logger(KLoggerType.DEBUG, *messages)
    }

    fun show(vararg messages: Any?) {
        logger(KLoggerType.DEBUG, *messages)
    }


    fun i(vararg messages: Any?) {
        logger(KLoggerType.INFO, *messages)
    }

    fun w(vararg messages: Any?) {
        logger(KLoggerType.WARN, *messages)
    }

    fun e(vararg messages: Any?) {
        logger(KLoggerType.ERROR, *messages)
    }

    fun v(vararg messages: Any?) {
        logger(KLoggerType.VERBOSE, *messages)
    }


    fun d(message: String, throwable: Throwable) {
        logThrowable(KLoggerType.DEBUG, message, throwable)
    }

    fun i(message: String, throwable: Throwable) {
        logThrowable(KLoggerType.INFO, message, throwable)
    }

    fun w(message: String, throwable: Throwable) {
        logThrowable(KLoggerType.WARN, message, throwable)
    }

    fun e(message: String, throwable: Throwable) {
        logThrowable(KLoggerType.ERROR, message, throwable)
    }

    fun showError(message: String, throwable: Throwable) {
        logThrowable(KLoggerType.ERROR, message, throwable)
    }

    fun showException(message: String, throwable: Throwable) {
        logThrowable(KLoggerType.ERROR, message, throwable)
    }

}





