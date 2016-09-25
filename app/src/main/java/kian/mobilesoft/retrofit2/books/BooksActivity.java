package kian.mobilesoft.retrofit2.books;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import kian.mobilesoft.retrofit2.R;
import kian.mobilesoft.retrofit2.book.BookActivity;
import kian.mobilesoft.retrofit2.di.Injector;
import kian.mobilesoft.retrofit2.models.Book;
import timber.log.Timber;

public class BooksActivity extends AppCompatActivity implements BooksContract.View {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    private BooksAdapter booksAdapter;
    private BooksAdapter.BookItemListener itemListener = new BooksAdapter.BookItemListener() {

        @Override
        public void onBookClick(long id) {
            Timber.d("book clicked id: %d", id);
            Intent intent = new Intent(BooksActivity.this, BookActivity.class);
            intent.putExtra(BookActivity.EXTRA_BOOK_ID, id);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        BooksPresenter booksPresenter = new BooksPresenter(this, Injector.provideBookService());
        booksAdapter = new BooksAdapter(this, new ArrayList<Book>(0), itemListener);

        booksPresenter.initDataSet();

        configureLayout();
    }

    @Override
    public void showBooks(List<Book> books) {
        booksAdapter.updateBooks(books);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, R.string.books_loading_unsuccessful, Toast.LENGTH_SHORT).show();
    }

    private void configureLayout() {
        setSupportActionBar((Toolbar) ButterKnife.findById(this, R.id.toolbar));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(booksAdapter);
        recyclerView.setHasFixedSize(true);
    }

}
