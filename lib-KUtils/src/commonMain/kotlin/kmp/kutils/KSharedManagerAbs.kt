package kmp.kutils


interface KShared {

    fun getAll(): Map<String, *>?
    fun getString(key: String, defValue: String): String?
    fun getStringSet(key: String, defValues: Set<String>?): Set<String?>?
    fun getInt(key: String, defValue: Int): Int
    fun getLong(key: String, defValue: Long): Long
    fun getFloat(key: String, defValue: Float): Float
    fun getBoolean(key: String, defValue: Boolean): Boolean
    operator fun contains(key: String): Boolean
    fun edit()

    // Editor
    fun putString(key: String, value: String?)
    fun putStringSet(key: String, values: Set<String?>?)
    fun putInt(key: String, value: Int)
    fun putLong(key: String, value: Long)
    fun putFloat(key: String, value: Float)
    fun putBoolean(key: String, value: Boolean)
    fun remove(key: String)
    fun clear()
    fun commit(): Boolean
    fun apply()
}


abstract class KSharedManagerAbs(val kShared: KShared) {

    fun save() = kShared.commit()

    fun put(key: String, value: Boolean) {
        putValue(key, value)
        save()
    }

    fun put(key: String, value: String) {
        putValue(key, value)
        save()
    }

    fun put(key: String, value: Int) {
        putValue(key, value)
        save()
    }

    fun put(key: String, value: Float) {
        putValue(key, value)
        save()
    }

    fun put(key: String, value: Long) {
        putValue(key, value)
        save()
    }

    fun put(key: String, value: Any) {
        putValue(key, value)
    }

    fun remove(key: String) {
        if (kShared.contains(key)) {
            kShared.remove(key)
            save()
        }
    }

    operator fun contains(key: String): Boolean {
        return kShared.contains(key)
    }

    /**
     * WARNING
     */
    fun deleteAll() {
        kShared.clear()
        save()
    }
    // private inline fun <reified T> getValue(key: String, defaultValue: T): T {


    fun get(key: String, defVal: String = ""): String {
        return getValue(key, defVal) ?: defVal
    }

    fun get(key: String, defVal: Int = -1): Int {
        return getValue(key, defVal) ?: defVal
    }

    fun get(key: String, defVal: Float = -1f): Float {
        return getValue(key, defVal) ?: defVal
    }

    fun get(key: String, defVal: Boolean = false): Boolean {
        return getValue(key, defVal) ?: defVal
    }


    inline fun <reified T> putValue(key: String, value: T) {
        when (value) {
            is String -> kShared.putString(key, value)
            is Int -> kShared.putInt(key, value)
            is Boolean -> kShared.putBoolean(key, value)
            is Float -> kShared.putFloat(key, value)
            is Long -> kShared.putLong(key, value)
            (value as? Set<String> != null) -> {
                kShared.putStringSet(key, value as Set<String>)
            }

            else -> throw UnsupportedOperationException("Type not supported")
        }
        save()
    }

    inline fun <reified T> getValue(key: String, defaultValue: T): T {
        return when (defaultValue) {
            is String -> kShared.getString(key, defaultValue) as T
            is Int -> kShared.getInt(key, defaultValue) as T
            is Boolean -> kShared.getBoolean(key, defaultValue) as T
            is Float -> kShared.getFloat(key, defaultValue) as T
            is Long -> kShared.getLong(key, defaultValue) as T
            is Set<*> -> {
                val setDefaultValue = defaultValue as? Set<String> ?: emptySet()
                kShared.getStringSet(key, setDefaultValue) as T
            }

            else -> throw UnsupportedOperationException("Type not supported")
        }
    }

    val allData: Map<String, *>?
        get() = kShared.getAll()

}