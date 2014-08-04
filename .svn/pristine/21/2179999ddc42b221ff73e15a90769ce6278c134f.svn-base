package org.hy.foundation.utils.interceptor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hy.foundation.utils.proxy.HibernateProxyCleaner;
import org.springframework.stereotype.Component;



@SuppressWarnings({ "rawtypes", "unchecked" })
@Component
@Aspect
public class EntityCleanerInterceptor {
	final Logger LOG = Logger.getLogger(EntityCleanerInterceptor.class);

	@Pointcut("execution(* com.suntang.pubopinion_analysis.service.*.* (..))")
	public void adviceMethods() {
	}

	@Around("adviceMethods()")
	public Object execute(ProceedingJoinPoint proceedingJoinPoint)
			throws Throwable {

		Object obj = null;
		long startTime = System.currentTimeMillis();
		LOG.debug("Around method executed.. " + proceedingJoinPoint.getClass());
		obj = proceedingJoinPoint.proceed();
		LOG.debug("Executing completed..");
		long timeTaken = System.currentTimeMillis() - startTime;
		LOG.debug("Execution time: " + timeTaken + " milliseconds.");

		try {
//			if (obj instanceof PersistentSet) { // PersistentSet 特定转换模式
//				Set result = new HashSet();
//				result.addAll((PersistentSet) obj);
//				obj = result;
//			}

//			if (obj instanceof ArrayList || obj instanceof HashSet)
//				obj = iteratorClone(obj);
//			else if (obj instanceof HashMap)
//				obj = ((HashMap) obj).clone();
//			else if (obj instanceof BasicEntity)
//				obj = ((BasicEntity) obj).clone();
			obj=copy(obj);
			HibernateProxyCleaner.cleanObject(obj, null);
		} catch (Exception e) {
			LOG.error("Entity cleaner excute error! ", e);
			obj = null;
		}

		return obj;
	}
	
	private Object copy(Object orig) {
        Object obj = null;
        try {
            // Write the object out to a byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(orig);
            out.flush();
            out.close();

            // Make an input stream from the byte array and read
            // a copy of the object back in.
            ObjectInputStream in = new ObjectInputStream(
                new ByteArrayInputStream(bos.toByteArray()));
            obj = in.readObject();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        return obj;
    }


}