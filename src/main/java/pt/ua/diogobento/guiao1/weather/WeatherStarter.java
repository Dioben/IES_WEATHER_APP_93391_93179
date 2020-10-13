package pt.ua.diogobento.guiao1.weather;

import pt.ua.diogobento.guiao1.weather.impa_client.CityForecast;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import pt.ua.diogobento.guiao1.weather.impa_client.IpmaCityForecast;
import pt.ua.diogobento.guiao1.weather.impa_client.IpmaService;

import java.util.ListIterator;
import java.util.logging.Logger;

/**
 * demonstrates the use of the IPMA API for weather forecast
 */
public class WeatherStarter {

    private static final int CITY_ID_AVEIRO = 1010500;
    /*
    loggers provide a better alternative to System.out.println
    https://rules.sonarsource.com/java/tag/bad-practice/RSPEC-106
     */
    private static final Logger logger = Logger.getLogger(WeatherStarter.class.getName());

    public static void  main(String[] args ) {

        /*
        get a retrofit instance, loaded with the GSon lib to convert JSON into objects
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IpmaService service = retrofit.create(IpmaService.class);
        Call<IpmaCityForecast> callSync = service.getForecastForACity(Integer.parseInt(args[0]));

        try {
            Response<IpmaCityForecast> apiResponse = callSync.execute();
            IpmaCityForecast forecast = apiResponse.body();

            if (forecast != null) {
                System.out.print("Dados metereológicos fornecidos por "+forecast.getOwner()+" relativos a "+forecast.getCountry()+"\n\n");
                ListIterator<CityForecast> x = forecast.getData().listIterator();
                while (x.hasNext()) {
                    CityForecast info = x.next();
                    System.out.print("Dados relativos a " + info.getLatitude() + "," + info.getLongitude() + " na data " + info.getForecastDate() + ":\n");
                    System.out.println("\tTemperaturas de " + info.getTMin() + " a " + info.getTMax());
                    System.out.println("\tProbabilidade de precipitação: " + info.getPrecipitaProb());
                    System.out.println("\tVento de classe " + info.getClassWindSpeed() + " na direção " + info.getPredWindDir() + "\n");

                }
            } else {
                logger.info( "No results!");
            }
            System.exit(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
