package research.m;

import java.util.Date;

/**
 * Klasa reprezentująca instancję badań.
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

    /**
     * Tworzy nową instancję badan, ustawiając aktualną datę jako początkową badań.
     */
    public ResearchData() {
        _currentState = ResearchState.STARTED;
        _startDate = new Date();
    }

    /**
     * Tworzy nową instancję badań o podanej nazwie bazowej powiązanego procesu na serwerze zdalnym
     * oraz podanej ilości tych procesów (data początkowa badań pozostaje nie ustawiona).
     * @param screenName nazwa bazowa zdalnego procesu, powiązanego z tą instancją badań
     * @param screensCount liczba uruchomionych procesów na zdalnym serwerze
     */
    public ResearchData(String screenName, int screensCount) {
        _screenBaseName= screenName;
        _screensCount = screensCount;
    }

    /**
     * Zwraca informację o aktualnym statusie badań.
     * @return aktualny status badań
     */
    public ResearchState getCurrentState() {
        return _currentState;
    }

    /**
     * Ustawia podany status jako aktualny.
     * @param currentState status, który ma zostać ustawiony dla badań
     */
    public void setCurrentState(ResearchState currentState) {
        _currentState = currentState;
    }

    /**
     * Zwraca bazową nazwę zdalnego procesu powiązanego z tą instancją badań.
     * @return nazwa bazowa zdalnego procesu
     */
    public String getRelatedScreenBaseName() {
        return _screenBaseName;
    }

    /**
     * Ustawia podaną nazwę jako bazową dla procesu zdalnego powiązanego z tą instancją badań.
     * @param screenName nowa nazwa bazowa zdalnego procesu
     */
    public void setRelatedScreenBaseName(String screenName) {
        _screenBaseName = screenName;
    }

    /**
     * Zwraca ilość zdalnych procesów domyślnie uruchamianych dla tej instancji badań.
     * @return ilość zdalnych procesów uruchamianych dla tej instancji badań
     */
    public int getRelatedScreensCount() {
        return _screensCount;
    }

    /**
     * Ustawia ilość procesów domyślnie uruchamianych dla tej instancji badań. 
     * @param screensCount nowa ilość zdalnych procesów
     */
    public void setRelatedScreensCount(int screensCount) {
        _screensCount = screensCount;
    }

    /**
     * Zwraca datę rozpoczęcia badań dla tej instancji.
     * @return data rozpoczęcia badań lub null, jeżeli badanie nie zostały rozpoczęte
     */
    public Date getStartDate() {
        return _startDate;
    }

    /**
     * Ustawia datę rozpoczęcia badań na podaną w parametrze.
     * @param startDate data rozpoczęcia badań
     */
    public void setStartDate(Date startDate) {
        _startDate = startDate;
    }

    /**
     * Zwraca datę zakończenia badań.
     * @return data zakończenia badań lub null, jeżeli badania nie zostały zakończone
     */
    public Date getEndDate() {
        return _endDate;
    }

    /**
     * Ustawia datę zakończenia badań na tą podaną w parametrze.
     * @param endDate data zakończenia badań
     */
    public void setEndDate(Date endDate) {
        _endDate = endDate;
    }

    /**
     * Zwraca całkowitą ilość serwerów, które biorą udział w badaniach.
     * @return ilość serwerów do przebadania
     */
    public int getServersTotal() {
        return _serversTotal;
    }

    /**
     * Ustawia całkowitą ilość serwerów, które biorą udział w badaniach.
     * @param serversTotal ilość serwerów do przebadania
     */
    public void setServersTotal(int serversTotal) {
        this._serversTotal = serversTotal;
    }

    /**
     * Zwraca ilość dotychczas przebadanych serwerów.
     * @return ilość przebadanych serwerów
     */
    public int getServersCompleted() {
        return _serversCompleted;
    }

    /**
     * Ustawia ilość dotychczas przebadanych serwerów.
     * @param serversCompleted ilość przebadanych serwerów
     */
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
