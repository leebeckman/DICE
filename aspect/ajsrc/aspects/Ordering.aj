package aspects;

public aspect Ordering {
	declare precedence: GeneralTracker, DBCPTaint, RequestParameterTaint;
}
