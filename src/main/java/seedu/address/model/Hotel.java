package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.address.model.hotel.room.Room;
import seedu.address.model.hotel.room.Tier;
import seedu.address.model.hotel.room.UniqueRoomList;
import seedu.address.model.ids.RoomId;

/**
 * Storing hotel's details: rooms, booking
 */
public class Hotel implements ReadOnlyHotel {
    private final UniqueRoomList roomList;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        roomList = new UniqueRoomList();
    }

    private final ArrayList<Tier> tierList;

    /**
     * Create new empty hotel.
     */
    public Hotel() {
        tierList = new ArrayList<>();
    }

    /**
     * Create new hotel from ReadOnlyHotel
     */
    public Hotel(ReadOnlyHotel toBeCopied) {
        this();
        requireNonNull(toBeCopied);
        resetData(toBeCopied);
        this.tierList.addAll(toBeCopied.getImmutableTierList());
    }

    @Override
    public ObservableList<Room> getImmutableRoomList() {
        return null;
    }

    /**
     * Get the room list.
     * @return a room list.
     */
    public ObservableList<Room> getRoomList() {
        return roomList.asUnmodifiableObservableList();
    }


    //// list overwrite operations

    /**
     * Replaces the contents of the room list with {@code rooms}.
     * {@code rooms} must not contain duplicate rooms.
     */
    public void setRooms(List<Room> rooms) {
        this.roomList.setRooms(rooms);
    }

    /**
     * Replaces the given room {@code target} in the list with {@code editedRoom}.
     * {@code target} must exist in the address book.
     * The room identity of {@code editedRoom} must not be the same as another existing room in the hotel.
     */
    public void setRooms(Room target, Room editedRoom) {
        requireNonNull(editedRoom);

        roomList.setRoom(target, editedRoom);
    }

    /**
     * Resets the existing data of this {@code Hotel} with {@code newData}.
     */
    public void resetData(ReadOnlyHotel newData) {
        requireNonNull(newData);

        setRooms(newData.getRoomList());
    }

    //// person-level operations

    /**
     * Returns true if a room with the same identity as {@code room} exists in the hotel.
     */
    public boolean hasRoom(Room room) {
        requireNonNull(room);
        return roomList.contains(room);
    }

    @Override
    public ObservableList<Tier> getImmutableTierList() {
        return FXCollections.observableArrayList(tierList);
    }

    /**
     * check room num exists.
     */
    public boolean hasRoom(String roomNum) {
        for (Room room: roomList) {
            if (room.hasName(roomNum)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return a room with matching room Id
     * @param roomId
     * @return
     */
    public Optional<Room> findRoomWithRoomId(RoomId roomId) {
        return roomList.asUnmodifiableObservableList()
                .stream()
                .filter(u -> u.getRoomId().equals(roomId))
                .findFirst();
    }

    /**
     * Return a room with matching room number
     * @param roomNum
     * @return
     */
    public Optional<Room> getRoom(String roomNum) {
        return roomList.asUnmodifiableObservableList()
                .stream()
                .filter(u -> u.getRoomNum().equals(roomNum))
                .findFirst();
    }

    /**
     * Adds a room to the hotel.
     * The room must not already exist in the address book.
     */
    public void addRoom(Room r) {
        roomList.add(r);
    }

    /**
     * find a room
     */
    private Room findSureRoom(String roomNum) {
        for (Room room: roomList) {
            if (room.hasName(roomNum)) {
                return room;
            }
        }
        return null;
    }


    /**
     * check a if a tier exists
     */
    public boolean hasTier(Tier otherTier) {
        for (Tier tier: tierList) {
            if (tier.equals(otherTier)) {
                return true;
            }
        }
        return false;
    }

    /**
     * add a new room
     */
    public void addRoom(String roomNum) {
        Room newRoom = new Room(roomNum);
        roomList.add(newRoom);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeRoom(Room key) {
        roomList.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return roomList.asUnmodifiableObservableList().size() + " rooms";
        // TODO: refine later
    }

    /**
     * Populates room list
     */
    public void fillRoomList() {
        for (int i = 0; i < 10; i++) {
            roomList.add(new Room(Integer.toString(i), new Tier()));
        }
    }
    /**
     * adds a new tier.
     */
    public void addTier(Tier tier, ArrayList<String> roomNums) {
        tierList.add(tier);

        for (String roomNum: roomNums) {
            if (hasRoom(roomNum)) {
                Room current = findSureRoom(roomNum);
                assert current != null;
                current.setTier(tier);
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof Hotel) {
            return this.roomList.equals(((Hotel) other).roomList);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return roomList.hashCode();
    }
}
