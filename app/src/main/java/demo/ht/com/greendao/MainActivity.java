package demo.ht.com.greendao;

import androidx.appcompat.app.AppCompatActivity;
import demo.ht.com.greendao.db.DaoSession;
import demo.ht.com.greendao.db.GreenBeanDao;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GreenBeanDao mGreenBeanDao;
    private Long i = 0l;
    private TextView mTv;
    private EditText mEt_query;
    private EditText mEtName;
    private EditText mEtDelete;
    private EditText mEtUpDate;
    private DaoSession mDaoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDaoSession = MyApplication.getInstance().getDaoSession();
        mGreenBeanDao  = mDaoSession.getGreenBeanDao();
        mTv = findViewById(R.id.tv);
        mEt_query = findViewById(R.id.et_query);
        mEtName = findViewById(R.id.et_name);
        mEtDelete = findViewById(R.id.et_delete);
        mEtUpDate = findViewById(R.id.et_update);
    }

    public void bt_insert(View view) {
        if (mEtName.getText().toString().trim().equals("")) {
            Toast.makeText(this, "请输入插入名字", Toast.LENGTH_SHORT).show();
            return;
        }
        mGreenBeanDao.insertOrReplace(new GreenBean(i++, "1", mEtName.getText().toString().trim(), 1));

//        mGreenBeanDao .insert();		//插入一条:   相同ID会报错
//        mGreenBeanDao .insertInTx();	//插入多条:
//        mGreenBeanDao .insertOrReplace();		//不存在则插入,存在则替换	单条
//        mGreenBeanDao .insertOrReplaceInTx();    //不存在则插入,存在则替换	   多条
    }

    public void bt_loadAll(View view) {
        List<GreenBean> greenBeans = mGreenBeanDao.loadAll();
        Log.i("szjloadAll", greenBeans.size() + "");
        StringBuffer stringBuffer = new StringBuffer();
        for (int j = 0; j < greenBeans.size(); j++) {
            stringBuffer.append(greenBeans.get(j).getName() + "\t\t\t" + greenBeans.get(j).getId() + "\n");
        }
        mTv.setText(stringBuffer.toString());
    }

    public void bt_query(View view) {

//       精确查找,若有多个或没有直接崩溃   查询的是当前ID
//        GreenBean unique = mGreenBeanDao.queryBuilder().where(GreenBeanDao.Properties.Id.eq(mEt_query.getText().toString())).unique();
//        Log.i("LangYangYangunique",unique.toString()+"");
//        Toast.makeText(this, ""+unique.toString(), Toast.LENGTH_SHORT).show();

//        精确查找 Id > 输入的值        where():相当于 &&
//        List<GreenBean> list = mGreenBeanDao.queryBuilder().where(GreenBeanDao.Properties.Id.gt(mEt_query.getText().toString())).list();
        //精确查找 Id < 输入的值
//        List<GreenBean> list = mGreenBeanDao.queryBuilder().where(GreenBeanDao.Properties.Id.lt(mEt_query.getText().toString())).list();

//        whereOr  相当于 ||
//        mGreenBeanDao .queryBuilder().whereOr(GreenBeanDao.Properties.Name.eq(mEt_query.getText().toString()),
//                GreenBeanDao.Properties.Id.eq(3)).list();

        //模糊查询
        //注意:得先插入小明
//        List<GreenBean> list = mGreenBeanDao.queryBuilder().where(GreenBeanDao.Properties.Name.like("小明%")).list();
//        List<GreenBean> list = mGreenBeanDao.queryBuilder().where(GreenBeanDao.Properties.Name.like("%小明%")).list();
        List<GreenBean> list = mGreenBeanDao.queryBuilder().where(GreenBeanDao.Properties.Name.like("%小明")).list();


        StringBuffer stringBuffer = new StringBuffer();
        for (int j = 0; j < list.size(); j++) {
            stringBuffer.append(list.get(j).getName() + "\t\t\t" + list.get(j).getId() + "\n");
        }


        mTv.setText(stringBuffer.toString());
    }

    public void bt_deleteAll(View view) {
        mGreenBeanDao.deleteAll();        //删除所有数据
    }

    public void bt_delete(View view) {
        if (mEtDelete.getText().toString().trim().equals("")) {
            Toast.makeText(this, "请输入删除ID", Toast.LENGTH_SHORT).show();
            return;
        }
//        mGreenBeanDao .delete();		//删除一条
        mGreenBeanDao.deleteByKey(Long.valueOf(mEtDelete.getText().toString().trim()));        //按照Key(键) id删除
//        mGreenBeanDao .deleteInTx();		//删除多个对象
//        Long[] keys = {5L,6L};
//        mGreenBeanDao .deleteByKeyInTx(keys);		//以Key(id)删除多个对象


    }

    int index = 1001;

    public void bt_bpdate(View view) {
        if (mEtUpDate.getText().toString().trim().equals("")) {
            Toast.makeText(this, "请输入修改ID", Toast.LENGTH_SHORT).show();
            return;
        }
        //注意:name不能重复,因为引用了 @Unique 注解
        mGreenBeanDao.update(new GreenBean(Long.valueOf(mEtUpDate.getText().toString().trim()), "已修改", "已修改" + index++, index++));
//        mGreenBeanDao.updateInTx(List<GreenBean>); //修改多个 ,通过Key修改
    }

    public void bt_clear_cache_all(View view) {
        mDaoSession.clear();//   清理缓存:      MyApplication.getInstance().getDaoSession();
        mGreenBeanDao.detachAll();//       清理所有缓存:
    }
}
