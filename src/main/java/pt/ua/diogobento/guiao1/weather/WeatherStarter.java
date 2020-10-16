package pt.ua.diogobento.guiao1.weather;

import pt.ua.diogobento.guiao1.weather.impa_client.CityForecast;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import pt.ua.diogobento.guiao1.weather.impa_client.IpmaCityForecast;
import pt.ua.diogobento.guiao1.weather.impa_client.IpmaService;

import java.util.Arrays;
import java.util.HashMap;
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
        HashMap<String,Integer> cities = new HashMap<>();
        final String[] CITYNAMES = {"Aveiro","Beja","Braga","Bragança","Castelo Branco","Coimbra","Évora","Faro","Guarda","Leiria","Lisboa","Portalegre","Porto","Santarém","Setúbal","Viana do Castelo","Vila Real","Viseu","Funchal","Porto Santo","Vila do Porto","Ponta Delgada","Angra do Heroísmo","Santa Cruz da Graciosa","Velas","Madalena","Horta","Santa Cruz das Flores","Vila do Corvo"};
        final int[] CODES = {1010500,1020500,1030300,1040200,1050200,1060300,1070500,1080500,1090700,1100900,1110600,1121400,1131200,1141600,1151200,1160900,1171400,1182300,2310300,2320100,3410100,3420300,3430100,3440100,3450200,3460200,3470100,3480200,3490100};
        boolean ok = false;
        for(int i=0;i<CITYNAMES.length;i++){
            cities.put(CITYNAMES[i].toLowerCase(),CODES[i]);
            if (CITYNAMES[i].toLowerCase().equals(args[0].toLowerCase())) ok = true; //we can satisfy request
        }
        if (!ok){
            System.err.println("LOCALIDADE INVÁLIDA,\nLOCALIDADES VÁLIDAS:\n");
            for (String x: CITYNAMES)
            System.err.println(x);
        }
        /*
        get a retrofit instance, loaded with the GSon lib to convert JSON into objects
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IpmaService service = retrofit.create(IpmaService.class);
        Call<IpmaCityForecast> callSync = service.getForecastForACity(cities.get(args[0].toLowerCase()));

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
