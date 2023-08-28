import android.os.Parcel
import android.os.Parcelable

data class EmployeeDetailsResponse(
    val data: EmployeeDetailsData,
    val Error: Boolean,
    val ErrorMsg: String?,
    val ErrorCode: String?,
    val ErrorID: String?
)

data class EmployeeDetailsData(
    val Items: List<DetailItem>,
    val Groups: List<DetailGroup>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(DetailItem.CREATOR) ?: emptyList(),
        parcel.createTypedArrayList(DetailGroup.CREATOR) ?: emptyList()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeTypedList(Items)
        dest.writeTypedList(Groups)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EmployeeDetailsData> {
        override fun createFromParcel(parcel: Parcel): EmployeeDetailsData {
            return EmployeeDetailsData(parcel)
        }

        override fun newArray(size: Int): Array<EmployeeDetailsData?> {
            return arrayOfNulls(size)
        }
    }
}

data class DetailItem(
    val Name: String,
    val Value: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(Name)
        dest.writeString(Value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DetailItem> {
        override fun createFromParcel(parcel: Parcel): DetailItem {
            return DetailItem(parcel)
        }

        override fun newArray(size: Int): Array<DetailItem?> {
            return arrayOfNulls(size)
        }
    }
}

data class DetailGroup(
    val Name: String,
    val Items: List<DetailItem>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.createTypedArrayList(DetailItem.CREATOR) ?: emptyList()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(Name)
        dest.writeTypedList(Items)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DetailGroup> {
        override fun createFromParcel(parcel: Parcel): DetailGroup {
            return DetailGroup(parcel)
        }

        override fun newArray(size: Int): Array<DetailGroup?> {
            return arrayOfNulls(size)
        }
    }
}
