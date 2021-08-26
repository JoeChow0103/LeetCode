import java.util.*;

public class RestaurantTableDistribution {
    /*
    BEHAVIOR
    check-in
    assignPartyToTable
    addToWaitingList

    ABSTRACT
    Party: id, size, preference, name, tel, arrive_at, table; Status:(waiting, eating, leaving)
                Category:(Big/ Regular)
    Table: id, capability, location, tableType: (bar, table, window), Party, Category:(Big Table/ Regular Table)

    followup:
    1. avoid big table with small number party, add category;
    2. telephone reservation. don't know if arrive.
    solution: sorted array, easy to implement comparing to bst
     */
}

class Party {

}

class RestaurantService {
    private Queue<Party> waitingList;
    private List<Party> reservationList; // sorted by reservation time
}
