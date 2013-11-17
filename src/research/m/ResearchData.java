package research.m;

import java.util.Date;

/**
 *
 * @author mrkaczor
 */
public class ResearchData {

    private ResearchState _currentState;
    private String _screenName;
    private Date _startDate;
    private int _serversTotal;
    private int _serversCompleted;

    public ResearchData() {
        _currentState = ResearchState.STARTED;
        _startDate = new Date();
    }

    public ResearchData(String screenName) {
        _screenName= screenName;
    }

    public ResearchState getCurrentState() {
        return _currentState;
    }

    public void setCurrentState(ResearchState currentState) {
        _currentState = currentState;
    }

    public String getRelatedScreenName() {
        return _screenName;
    }

    public void setRelatedScreenName(String screenName) {
        _screenName = screenName;
    }

    public Date getStartDate() {
        return _startDate;
    }

    public void setStartDate(Date startDate) {
        _startDate = startDate;
    }

    public int getServersTotal() {
        return _serversTotal;
    }

    public void setServersTotal(int serversTotal) {
        this._serversTotal = serversTotal;
    }

    public int getServersCompleted() {
        return _serversCompleted;
    }

    public void setServersCompleted(int serversCompleted) {
        this._serversCompleted = serversCompleted;
    }

    @Override
    public String toString() {
        String researchData = this.getClass().getName() + ":";
        researchData += " [currentState="+_currentState+"]";
        researchData += " [screenName="+_screenName+"]";
        researchData += " [startDate="+_startDate+"]";
        researchData += " [serversTotal="+_serversTotal+"]";
        researchData += " [serversCompleted="+_serversCompleted+"]";
        return researchData;
    }

}
