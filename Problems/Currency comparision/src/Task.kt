import java.util.*

enum class Countries(val country: String, val currency: String){
    GER("Germany","Euro"),
    MALI("Mali",	"CFA franc"),
    DOM("Dominica",	"Eastern Caribbean dollar"),
    CAN("Canada",	"Canadian dollar"),
    SPA("Spain",	"Euro"),
    AUS("Australia",	"Australian dollar"),
    BRA("Brazil",	"Brazilian real"),
    SEN("Senegal",	"CFA franc"),
    FRA("France",	"Euro"),
    GRE("Grenada",	"Eastern Caribbean dollar"),
    KIR("Kiribati",	"Australian dollar"),
    NULL("","");
    companion object{
        fun findByCountry(country: String): Countries{
            for (enum in Countries.values()){
                if (country == enum.country) return enum
            }
            return NULL
        }
    }


}
fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    println(Countries.findByCountry(input.next()).currency == Countries.findByCountry(input.next()).currency)
}