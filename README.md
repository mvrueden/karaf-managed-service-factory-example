# Karaf ManagedServiceFactory Example
I encountered an issue while upgrading from Karaf 4.1.5 to 4.2.3. 
This provides an example to reproduce the problem locally in order to find a solution for it.
See issue [KARAF-6181](https://issues.apache.org/jira/browse/KARAF-6181) for more details.

## How to reproduce

- Checkout this repository
- Build the code: `mvn clean install`
- Download latest Apache Karaf 4.2.3 Distribution from [here](http://www.apache.org/dyn/closer.lua/karaf/4.2.3/apache-karaf-4.2.3.tar.gz).
- Extract the archive
- Start the container `./bin/karaf`.
- Install `aries-blueprint`: `feature:install aries-blueprint`.
- Install the bundle: `install -s mvn:org.opennms.dummy/managed-service-factory/1.0-SNAPSHOT`
- Verify via `list`
- Now add some configuration files, e.g.

```
config:edit org.opennms.dummy.services-1
config:property-set key value
config:update
```

Now with a `log:tail` you can see, that there is no log output.
With `config:list "(service.pid=org.opennms*)"` you can see, that there is no `service.factoryPid` assigned, indicating it was not correctly "populated". Also restarting the container did not work.
