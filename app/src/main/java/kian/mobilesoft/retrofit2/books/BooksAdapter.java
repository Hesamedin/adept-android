package kian.mobilesoft.retrofit2.books;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import kian.mobilesoft.retrofit2.R;
import kian.mobilesoft.retrofit2.models.Book;
import timber.log.Timber;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {

    private WeakReference<Context> context;
    private List<Book> books;
    private BookItemListener itemListener;

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        @Bind(R.id.titleTextView) TextView titleTextView;
        @Bind(R.id.authorTextView) TextView authorTextView;
        @Bind(R.id.publishedTextView) TextView publishedTextView;
        @Bind(R.id.pagesTextView) TextView pagesTextView;
        @Bind(R.id.imageView) ImageView imageView;

        BookItemListener itemListener;

        public ViewHolder(View v, BookItemListener itemListener) {
            super(v);
            ButterKnife.bind(this, v);

            this.itemListener = itemListener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick (View v)
        {
            Book book = getItem(getAdapterPosition());
            this.itemListener.onBookClick( book.getId() );
        }
    }

    public BooksAdapter (Context context, List<Book> books, BookItemListener itemListener) {
        this.context = new WeakReference<>(context);
        this.books = books;
        this.itemListener = itemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.book_row_item, viewGroup, false);

        return new ViewHolder(v, this.itemListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Timber.d("Element " + position + " set.");

        Book book = books.get(position);

        viewHolder.titleTextView.setText(book.getTitle());
        viewHolder.authorTextView.setText(book.getAuthor());

        Context contextLocal = context.get();
        if (contextLocal != null) {
            viewHolder.publishedTextView.setText(book.getDisplayDate());
            viewHolder.pagesTextView.setText(
                    String.format(contextLocal.getString(R.string.pages_label), book.getNumberOfPages()));

            Picasso.with(contextLocal)
                    .load(book.getImageUrl())
                    .resize(80, 108)
                    .centerInside()
                    .into(viewHolder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void updateBooks(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    private Book getItem(int adapterPosition) {
        return books.get(adapterPosition);
    }

    public interface BookItemListener {
        void onBookClick(long id);
    }
}
