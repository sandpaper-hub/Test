data class Animal(val name: String) {
    var year: Int = 0
        set(value) {
            field = value + 1
        }
        get() {
            return field + 1
        }
    constructor(brand: String, model: String) : this(brand + " " + model)
}