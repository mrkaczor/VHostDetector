package research.m;

import java.util.Date;

/**
 *
 * @author mrkaczor
 */
public class ResearchData {

    private ResearchState _currentState;
    private String _screenBaseName;
    private int _screensCount;
    private Date _startDate;
    private Date _endDate;
    private int _serversTotal;
    private int _serversCompleted;

    public ResearchData() {
        _currentState = ResearchState.STARTED;
        _startDate = new Date();
    }

    public ResearchData(String screenName, int screensCount) {
        _screenBaseName= screenName;
        _screensCount = screensCount;
    }

    public ResearchState getCurrentState() {
        return _currentState;
    }

    public void setCurrentState(ResearchState currentState) {
        _currentState = currentState;
    }

    public String getRelatedScreenBaseName() {
        return _screenBaseName;
    }

    public void setRelatedScreenBaseName(String screenName) {
        _screenBaseName = screenName;
    }

    public int getRelatedScreensCount() {
        return _screensCount;
    }

    public void setRelatedScreensCount(int screensCount) {
        _screensCount = screensCount;
    }

    public Date getStartDate() {
        return _startDate;
    }

    public void setStartDate(Date startDate) {
        _startDate = startDate;
    }

    public Date getEndDate() {
        return _endDate;
    }

    public void setEndDate(Date endDate) {
        _endDate = endDate;
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
        researchData += " [screenBaseName="+_screenBaseName+"]";
        researchData += " [screensCount="+_screensCount+"]";
        researchData += " [startDate="+_startDate+"]";
        researchData += " [endDate="+_endDate+"]";
        researchData += " [serversTotal="+_serversTotal+"]";
        researchData += " [serversCompleted="+_serversCompleted+"]";
        return researchData;
    }

}
