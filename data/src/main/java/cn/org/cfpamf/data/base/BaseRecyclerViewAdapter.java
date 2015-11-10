package cn.org.cfpamf.data.base;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import cn.org.cfpamf.data.listener.OnItemClickListener;
import cn.org.cfpamf.data.listener.OnItemLongClickListener;


public abstract class BaseRecyclerViewAdapter<M, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {
    /**
     * click listener
     */
    protected OnItemClickListener mOnItemClickListener;
    /**
     * long click listener
     */
    protected OnItemLongClickListener mOnItemLongClickListener;


    /**
     * set a long click listener
     *
     * @param onItemLongClickListener
     */
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    /**
     * set a click listener
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    /**
     * bind click listner to itemview
     *
     * @param vh       viewholder
     * @param position position
     */
    protected final void bindItemViewClickListener(VH vh, final int position) {
        if (mOnItemClickListener != null) {
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onClick(view, position);
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            vh.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemLongClickListener.onLongClick(v, position);
                    return true;
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        final M item = getItem(position);
        holder.itemView.setTag(item);
        bindDataToItemView(holder, position);
        bindItemViewClickListener(holder, position);
    }

    /**
     * bind data to itemview
     *
     * @param vh       viewholder
     * @param position position
     */
    protected abstract void bindDataToItemView(VH vh, int position);


    /**
     * inflate a itemView by viewgroup ,id ,etc
     *
     * @param viewGroup
     * @param layoutId
     * @return
     */
    protected View inflateItemView(ViewGroup viewGroup, int layoutId) {
        return inflateItemView(viewGroup, layoutId, false);
    }

    /**
     * inflate a itemView by viewgroup ,id ,etc
     *
     * @param viewGroup
     * @param layoutId
     * @param attach
     * @return
     */
    protected View inflateItemView(ViewGroup viewGroup, int layoutId, boolean attach) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, attach);
    }

    private ArrayList<M> items = new ArrayList<M>();

    public BaseRecyclerViewAdapter() {
        setHasStableIds(true);
    }

    public void add(@NonNull M object) {
        items.add(object);
        notifyDataSetChanged();
    }

    public void add(@NonNull int index, @NonNull M object) {
        items.add(index, object);
        notifyDataSetChanged();
    }

    public void addAll(@NonNull Collection<? extends M> collection) {
        if (collection != null) {
            items.addAll(collection);
            notifyDataSetChanged();
        }
    }

    public void replace(@NonNull int index, @NonNull M object) {
        items.remove(index);
        items.add(index, object);
        notifyDataSetChanged();
    }

    public void addAll(@NonNull M... items) {
        addAll(Arrays.asList(items));
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public boolean contains(M object) {
        if (items.contains(object)) {
            return true;
        }
        return false;
    }

    public void remove(M object) {
        items.remove(object);
        notifyDataSetChanged();
    }

    public ArrayList<M> getAll() {
        return items;
    }

    @CheckResult
    public M getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
