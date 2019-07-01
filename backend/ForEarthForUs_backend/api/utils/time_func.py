from datetime import datetime, timedelta, time

def get_today():
    today = datetime.now().date()
    return today

def calc_today():
    today = get_today()
    tomorrow = today + timedelta(1)
    today_start = datetime.combine(today, time())
    today_end = datetime.combine(tomorrow, time())
    return (today_start, today_end)