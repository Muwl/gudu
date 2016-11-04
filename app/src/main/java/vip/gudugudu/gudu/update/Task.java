package vip.gudugudu.gudu.update;

import android.app.Notification;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author 穆文磊
 *         <p>
 *         2014年7月29日 22:27:26
 *         <p>
 *         任务
 */
public class Task implements Parcelable {

    private int id;

    private String name;

    private int progress;

    private String icon;

    private Notification notification;

    private int status;

    private String uriPath = "";

    public Task() {

    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(icon);
        out.writeString(name);
        out.writeParcelable(notification, flags);
        out.writeInt(this.id);
        out.writeInt(this.status);
        out.writeInt(this.progress);
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    private Task(Parcel in) {
        icon = in.readString();
        name = in.readString();
        notification = in.readParcelable(Task.class.getClassLoader());
        id = in.readInt();
        status = in.readInt();
        progress = in.readInt();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUriPath() {
        return uriPath;
    }

    public void setUriPath(String uriPath) {
        this.uriPath = uriPath;
    }
}
