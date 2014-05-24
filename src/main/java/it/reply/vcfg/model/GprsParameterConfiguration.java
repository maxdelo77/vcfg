package it.reply.vcfg.model;

/**
 * Created by Massimo on 08/05/14.
 */
public class GprsParameterConfiguration{

    //COMM_CODE	SALES_CHANNEL	EVENT_KEY2  - BUNDLE_ID	RATE_MODEL	Rate Plan

    private String commonCode;
    private String salesChannel;
    private String eventKey;
    private String rateModel;
    private String ratePlan;

    public String getCommonCode() {
        return commonCode;
    }

    public void setCommonCode(String commonCode) {
        this.commonCode = commonCode;
    }

    public String getSalesChannel() {
        return salesChannel;
    }

    public void setSalesChannel(String salesChannel) {
        this.salesChannel = salesChannel;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getRateModel() {
        return rateModel;
    }

    public void setRateModel(String rateModel) {
        this.rateModel = rateModel;
    }

    public String getRatePlan() {
        return ratePlan;
    }

    public void setRatePlan(String ratePlan) {
        this.ratePlan = ratePlan;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "GprsParameterConfiguration{" +
                "commonCode='" + commonCode + '\'' +
                ", salesChannel='" + salesChannel + '\'' +
                ", eventKey='" + eventKey + '\'' +
                ", rateModel='" + rateModel + '\'' +
                ", ratePlan='" + ratePlan + '\'' +
                '}';
    }
}
