package davoodi.mahdi.oufa.components;

import android.content.ContentValues;

public class Owner {
    public static final String KEY_ID = "ownerID";
    public static final String KEY_NAME = "ownerName";
    public static final String KEY_PASSWORD = "ownerPasswordHash";
    public static final String KEY_WEALTH = "ownerTotalWealth";
    public static final String KEY_WIN = "ownerTotalWin";
    public static final String KEY_LOSS = "ownerTotalLoss";
    public static final String KEY_DRAW = "ownerTotalDraw";
    public static final String KEY_CUPS = "ownerTotalCups";


    private final int ownerID;
    private final String ownerName;
    private final long ownerPasswordHash;
    private final long ownerTotalWealth;
    private long ownerTotalWin, ownerTotalLoss, ownerTotalDraw;
    private int ownerTotalCups;

    public Owner(int ownerID,
                 String ownerName,
                 long ownerPasswordHash,
                 long ownerTotalWealth,
                 long ownerTotalWin,
                 long ownerTotalLoss,
                 long ownerTotalDraw,
                 int ownerTotalCups) {
        this.ownerID = ownerID;
        this.ownerName = ownerName;
        this.ownerPasswordHash = ownerPasswordHash;
        this.ownerTotalWealth = ownerTotalWealth;
        this.ownerTotalWin = ownerTotalWin;
        this.ownerTotalLoss = ownerTotalLoss;
        this.ownerTotalDraw = ownerTotalDraw;
        this.ownerTotalCups = ownerTotalCups;
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(KEY_ID, getOwnerID());
        values.put(KEY_NAME, getOwnerName());
        values.put(KEY_PASSWORD, getOwnerPasswordHash());
        values.put(KEY_WEALTH, getOwnerTotalWealth());
        values.put(KEY_WIN, getOwnerTotalWin());
        values.put(KEY_LOSS, getOwnerTotalLoss());
        values.put(KEY_DRAW, getOwnerTotalDraw());
        values.put(KEY_CUPS, getOwnerTotalCups());
        return values;
    }

    public static long passwordHash(String passwordTemp) {
        long passwordSign = 1;
        for (int i = 0; i < passwordTemp.length(); i++) {
            passwordSign = (passwordSign * 255 + passwordTemp.charAt(i)) % 1000000;
        }
        return passwordSign;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public long getOwnerPasswordHash() {
        return ownerPasswordHash;
    }

    public long getOwnerTotalWealth() {
        return ownerTotalWealth;
    }

    public long getOwnerTotalWin() {
        return ownerTotalWin;
    }

    public void setOwnerTotalWin(long ownerTotalWin) {
        this.ownerTotalWin = ownerTotalWin;
    }

    public long getOwnerTotalLoss() {
        return ownerTotalLoss;
    }

    public void setOwnerTotalLoss(long ownerTotalLoss) {
        this.ownerTotalLoss = ownerTotalLoss;
    }

    public long getOwnerTotalDraw() {
        return ownerTotalDraw;
    }

    public void setOwnerTotalDraw(long ownerTotalDraw) {
        this.ownerTotalDraw = ownerTotalDraw;
    }

    public int getOwnerTotalCups() {
        return ownerTotalCups;
    }

    public void setOwnerTotalCups(int ownerTotalCups) {
        this.ownerTotalCups = ownerTotalCups;
    }

}
