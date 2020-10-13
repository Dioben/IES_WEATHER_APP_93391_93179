package pt.ua.diogobento.guiao1.weather.impa_client;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * the data point with the forecast for a city in a day
 */
public class CityForecast {

    @SerializedName("precipitaProb")
    @Expose
    private String precipitaProb;
    @SerializedName("tMin")
    @Expose
    private String tMin;
    @SerializedName("tMax")
    @Expose
    private String tMax;
    @SerializedName("predWindDir")
    @Expose
    private String predWindDir;
    @SerializedName("idWeatherType")
    @Expose
    private Integer idWeatherType;
    @SerializedName("classWindSpeed")
    @Expose
    private Integer classWindSpeed;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("forecastDate")
    @Expose
    private String forecastDate;
    @SerializedName("latitude")
    @Expose
    private String latitude;

    /**
     * return prob prec.
     * @return String precipitação
     */
    public String getPrecipitaProb() {
        return precipitaProb;
    }

    /**
     * basic setter
     * @param precipitaProb value set
     */
    public void setPrecipitaProb(String precipitaProb) {
        this.precipitaProb = precipitaProb;
    }

    /**
     * basic getter
     * @return String
     */
    public String getTMin() {
        return tMin;
    }

    /**
     * basic setter
     * @param tMin min. temperature
     */
    public void setTMin(String tMin) {
        this.tMin = tMin;
    }

    /**
     * basic getter
     * @return String
     */
    public String getTMax() {
        return tMax;
    }

    /**
     * basic setter
     * @param tMax max temp.
     */
    public void setTMax(String tMax) {
        this.tMax = tMax;
    }

    /**
     * basic getter
     * @return String
     */
    public String getPredWindDir() {
        return predWindDir;
    }

    /**
     * basic setter
     * @param predWindDir wind dir.
     */
    public void setPredWindDir(String predWindDir) {
        this.predWindDir = predWindDir;
    }

    /**
     * basic getter
     * @return Integer
     */
    public Integer getIdWeatherType() {
        return idWeatherType;
    }

    /**
     * basic setter
     * @param idWeatherType weather type code
     */
    public void setIdWeatherType(Integer idWeatherType) {
        this.idWeatherType = idWeatherType;
    }

    /**
     * basic getter
     * @return Integer
     */
    public Integer getClassWindSpeed() {
        return classWindSpeed;
    }

    /**
     * basic setter
     * @param classWindSpeed how bad the wind is
     */
    public void setClassWindSpeed(Integer classWindSpeed) {
        this.classWindSpeed = classWindSpeed;
    }

    /**
     * basic getter
     * @return String
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * basic setter , provide value between -180 and 180
     * @param longitude String, min -180  max 180
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * basic getter
     * @return String
     */
    public String getForecastDate() {
        return forecastDate;
    }

    /**
     * basic setter, please provide a date format
     * @param forecastDate date
     */
    public void setForecastDate(String forecastDate) {
        this.forecastDate = forecastDate;
    }

    /**
     * basic getter
     * @return String
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * basic setter, provide value between -90 and 90
     * @param latitude min -90 max 90
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
